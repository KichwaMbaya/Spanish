package com.justkeepfaith.krygdistwe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class profilo extends AppCompatActivity {

    TextView user_details, bankaccount;
    Button editbank, deleteaccount;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        user_details = findViewById(R.id.user_details);
        bankaccount = (TextView) findViewById(R.id.bankaccount);
        editbank = findViewById(R.id.editbank);
        deleteaccount = findViewById(R.id.deleteaccount);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);

        String string = sharedPreferences.getString("first_name", "");
        String string2 = sharedPreferences.getString("middle_name", "");
        String string3 = sharedPreferences.getString("last_name", "");
        String string4 = sharedPreferences.getString("Email", "");
        String string5 = sharedPreferences.getString("phone_number", "");
        String string6 = sharedPreferences.getString("occupation", "");
        String string7 = sharedPreferences.getString("workplace", "");
        String string8 = sharedPreferences.getString("income", "");
        String string9 = sharedPreferences.getString("IDno", "");
        String string10 = sharedPreferences.getString("keen_name", "");
        String string11 = sharedPreferences.getString("relationship", "");
        String string12 = sharedPreferences.getString("keen_phone", "");

        String Routing = sharedPreferences.getString("r_number", "");
        String Account_number = sharedPreferences.getString("a_number", "");

        user_details.setText("\n1. Nombre: "+ string + " " + string2 + " " + string3 +
                "\n\n2. Correo electrónico: " + string4 +
                "\n\n3. Teléfono: " + string5 +
                "\n\n4. Ocupación: " + string6 +
                "\n\n5. Lugar de trabajo " + string7 +
                "\n\n6. DNI: " + string9 +
                "\n\n7. Garante: " + string10 +
                "\n\n8. Relación: " + string11 +
                "\n\n9. Teléfono: " +string12 + "\n");


        bankaccount.setText(Account_number);

        editbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profilo.this, bancarie_coordinate.class);
                startActivity(intent);
            }
        });

        deleteaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(profilo.this);
                dialog.setTitle("Estas segura");
                dialog.setMessage("Eliminar esta cuenta resultará en la eliminación completa de sus datos del sistema. Esta acción no se " +
                        "puede deshacer y ya no podrá acceder a la aplicación. \n\n" +
                        "Tenga en cuenta que si tiene dinero ahorrado en su cuenta, se le reembolsará dentro de los 5 días hábiles.");
                dialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!Conectivity.isConnectingToInternet(profilo.this)) {
                            Toast.makeText(profilo.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();

                        Toast.makeText(profilo.this, "Eliminada", Toast.LENGTH_SHORT).show();

                        android.os.Process.killProcess(android.os.Process.myPid());

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
}