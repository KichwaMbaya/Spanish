package com.justkeepfaith.krygdistwe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Contattaci extends AppCompatActivity {

    EditText sendtxt;
    Button sendbtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contattaci);

        sendtxt = (EditText) findViewById(R.id.sendtxt);
        sendbtn = findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacusmessage();
            }
        });
    }
    private void contacusmessage() {
        String csendtxt = sendtxt.getText().toString();

        if (csendtxt.isEmpty()) {
            sendtxt.setError("No puede estar vacía.");
            return;
        }
        if (!Conectivity.isConnectingToInternet(Contattaci.this)) {
            Toast.makeText(Contattaci.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog = new ProgressDialog(Contattaci.this);
        progressDialog.setMessage("Enviando...");
        progressDialog.setProgressStyle(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);//7000
                    Intent intent = new Intent(Contattaci.this, principale2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}