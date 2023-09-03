package com.justkeepfaith.krygdistwe;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class registrati extends AppCompatActivity {

    EditText rgsterfirst, rgstermiddle, rgsterlast, rgsteremail, rgsterphone, rgsterpin, rgsterconfirm;
    Button rgsterdob, rgsterbutton;
    DatePickerDialog datePickerDialog;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrati);

        rgsterfirst = findViewById(R.id.rgsterfirst);
        rgstermiddle = findViewById(R.id.rgstermiddle);
        rgsterlast = findViewById(R.id.rgsterlast);
        rgsteremail = findViewById(R.id.rgsteremail);
        rgsterphone = findViewById(R.id.rgsterphone);
        rgsterpin = findViewById(R.id.rgsterpin);
        rgsterconfirm = findViewById(R.id.rgsterconfirm);
        rgsterdob = findViewById(R.id.rgsterdob);
        rgsterbutton = findViewById(R.id.rgsterbutton);


        initDatePicker();
        rgsterdob.setText(getTodaysDate());

        if (!Conectivity.isConnectingToInternet(registrati.this)) {
            Toast.makeText(registrati.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
        }

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);

        rgsterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String first = rgsterfirst.getText().toString();
                String middle = rgstermiddle.getText().toString();
                String last = rgsterlast.getText().toString();
                String email = rgsteremail.getText().toString();
                String phone = rgsterphone.getText().toString();
                String pin = rgsterpin.getText().toString();
                String confirm = rgsterconfirm.getText().toString();

                if (first.isEmpty()){
                    rgsterfirst.setError("No puede estar vacía");
                    return;
                }
                if (middle.isEmpty()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("middle_name");
                    editor.commit();
                }
                if (last.isEmpty()){
                    rgsterlast.setError("No puede estar vacía");
                    return;
                }
                if (email.isEmpty()){
                    rgsteremail.setError("No puede estar vacía");
                    Toast.makeText(registrati.this, "No puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    rgsteremail.setError("IEmail inválido");
                    rgsteremail.requestFocus();
                    return;
                }
                if (phone.isEmpty()){
                    rgsterphone.setError("No puede estar vacía");
                    Toast.makeText(registrati.this, "Inserisci il numero di telefono", Toast.LENGTH_SHORT).show();
                }
                if (phone.length() < 7){
                    rgsterphone.setError("Demasiado corta");
                    Toast.makeText(registrati.this, "Demasiado corta", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() > 13){
                    rgsterphone.setError("Demasiado larga");
                    Toast.makeText(registrati.this, "Demasiado larga", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pin.isEmpty()){
                    rgsterpin.setError("No puede estar vacía");
                    Toast.makeText(registrati.this, "No puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pin.length() < 4){
                    rgsterpin.setError("Demasiado corta");
                    Toast.makeText(registrati.this, "Demasiado corta", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirm.isEmpty()){
                    rgsterconfirm.setError("No puede estar vacía");
                    Toast.makeText(registrati.this, "No puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!confirm.equals(pin)){
                    rgsterpin.setError("No coincide");
                    rgsterconfirm.setError("No coincide");
                    Toast.makeText(registrati.this, "No coincide", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(registrati.this);
                dialog.setTitle("¿Estás seguro?");
                dialog.setMessage("La información que ha proporcionado no se puede cambiar después del registro.");
                dialog.setPositiveButton("Proceder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("first_name", first);
                        editor.putString("middle_name", middle);
                        editor.putString("last_name", last);
                        editor.putString("Email", email);
                        editor.putString("phone_number", phone);
                        editor.putString("PIN", pin);

                        Integer logged_in = 2;
                        editor.putInt("logged", logged_in);

                        editor.commit();

                        progressDialog = new ProgressDialog(registrati.this);
                        progressDialog.setTitle("Espere por favor");
                        progressDialog.setMessage("Creando cuenta...");
                        progressDialog.setProgressStyle(0);
                        progressDialog.setMax(100);
                        progressDialog.show();
                        progressDialog.setCancelable(false);
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    Thread.sleep(6000);
                                    Intent intent = new Intent(registrati.this, accesso.class);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                progressDialog.dismiss();
                            }
                        }).start();

                    }
                });
                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int current = cal.get(YEAR);
        int age = 16;

        int year = current - age;
        int month = cal.get(MONTH);
        month = month + 1;
        int day = cal.get(DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                rgsterdob.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int current = cal.get(YEAR);
        int age = 16;

        int year = current - age;
        int month = cal.get(MONTH);
        month = month + 1;
        int day = cal.get(DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + ", " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "ENERO";
        if(month == 2)
            return "FEBRERO";
        if(month == 3)
            return "MARZO";
        if(month == 4)
            return "ABRIL";
        if(month == 5)
            return "MAYO";
        if(month == 6)
            return "JUNIO";
        if(month == 7)
            return "JULIO";
        if(month == 8)
            return "AGOSTO";
        if(month == 9)
            return "SEPTIEMBRE";
        if(month == 10)
            return "OCTUBRE";
        if(month == 11)
            return "NOVIEMBRE";
        if(month == 12)
            return "DICIEMBRE";

        //default should never happen
        return "AGOSTO";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}