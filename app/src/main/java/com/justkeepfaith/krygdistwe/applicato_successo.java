package com.justkeepfaith.krygdistwe;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class applicato_successo extends AppCompatActivity {

    Button donee, button_enterbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicato_successo);

        donee = findViewById(R.id.donee);
        button_enterbank = findViewById(R.id.button_enterbank);

        donee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 33) {

                    if (ContextCompat.checkSelfPermission(applicato_successo.this,
                            android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(applicato_successo.this);
                        dialog.setMessage("Permita notificaciones para esta aplicación para que se le notifique " +
                                "cuando su préstamo esté listo");
                        dialog.setPositiveButton("ESTÁ BIEN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ActivityCompat.requestPermissions(applicato_successo.this,
                                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);

                            }
                        });
                        AlertDialog alertDialog = dialog.create();
                        alertDialog.show();
                    }
                    else {
                        Intent intent = new Intent(applicato_successo.this, principale2.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(applicato_successo.this, principale2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        button_enterbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 33) {

                    if (ContextCompat.checkSelfPermission(applicato_successo.this,
                            android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){

                        AlertDialog.Builder dialog = new AlertDialog.Builder(applicato_successo.this);
                        dialog.setMessage("Permita notificaciones para esta aplicación para que se le notifique " +
                                "cuando su préstamo esté listo");
                        dialog.setPositiveButton("ESTÁ BIEN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ActivityCompat.requestPermissions(applicato_successo.this,
                                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);

                            }
                        });

                        AlertDialog alertDialog = dialog.create();
                        alertDialog.show();
                    }
                    else {
                        Intent intent = new Intent(applicato_successo.this, bancarie_coordinate.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Intent intent = new Intent(applicato_successo.this, bancarie_coordinate.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}