package com.justkeepfaith.krygdistwe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class prestito_quantita extends AppCompatActivity {

    TextView applitittle;
    EditText enteramount;
    Button submitt;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    String applimit, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestito_quantita);

        submitt = findViewById(R.id.submitt);
        enteramount = (EditText) findViewById(R.id.enteramount);
        applitittle = (TextView) findViewById(R.id.applitittle);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        applimit = sharedPreferences.getString("loan_limit", "123");
        name = sharedPreferences.getString("last_name", "Utente");
        applitittle.setText("Hola " + name + ", usted tiene un límite de préstamo actual de €" +(applimit));


        submitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestloan();
            }
        });
    }
    private void requestloan() {

        String wenteramount = enteramount.getText().toString().trim();

        if (wenteramount.isEmpty()) {
            enteramount.setError("No puede estar vacía");
            return;
        }
        if (!wenteramount.isEmpty()) {

            int wapplimit = Integer.parseInt(applimit);
            int ienteramount = Integer.parseInt(enteramount.getText().toString());

            if (ienteramount > wapplimit) {
                enteramount.setError("Tu límite es €" + applimit);
                return;
            }
            if (ienteramount < 5) {
                enteramount.setError("El importe mínimo es de €5");
                return;
            }
        }
        if (!Conectivity.isConnectingToInternet(prestito_quantita.this)) {
            Toast.makeText(prestito_quantita.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("loan_appliedx", wenteramount);
        editor.commit();

        progressDialog = new ProgressDialog(prestito_quantita.this);
        progressDialog.setMessage("Ahorro...");
        progressDialog.setProgressStyle(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);//8000
                    Intent intent = new Intent(prestito_quantita.this, prestito_periodo.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}