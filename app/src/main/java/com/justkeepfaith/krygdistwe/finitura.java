package com.justkeepfaith.krygdistwe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class finitura extends AppCompatActivity {

    EditText finoccup, finpow, finincome, finidno, finkeen, finrelation, finphone;
    Button finsubmit;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finitura);

        finoccup = findViewById(R.id.finoccup);
        finpow = findViewById(R.id.finpow);
        finincome = findViewById(R.id.finincome);
        finidno = findViewById(R.id.finidno);
        finkeen = findViewById(R.id.finkeen);
        finrelation = findViewById(R.id.finrelation);
        finphone = findViewById(R.id.finphone);
        finsubmit = findViewById(R.id.finsubmit);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String proce = sharedPreferences.getString("workplace", "");

        if (!proce.isEmpty()){

            Intent intent = new Intent(finitura.this, principale2.class);
            startActivity(intent);
            finish();
        }
        if (!Conectivity.isConnectingToInternet(finitura.this)) {
            Toast.makeText(finitura.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
        }

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Classified").document("Limits");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot dsnap = task.getResult();

                String Upper = dsnap.getString("Upper");
                String Lower = dsnap.getString("Lower");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Upper", Upper);
                editor.putString("Lower", Lower);
                editor.commit();
            }
        });

        finsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifyer();
            }
        });
    }
    private void verifyer(){
        String finoccu = finoccup.getText().toString();
        String finpo = finpow.getText().toString();
        String finincom = finincome.getText().toString();
        String finidn = finidno.getText().toString();
        String finkee = finkeen.getText().toString();
        String finrelatio = finrelation.getText().toString();
        String phon = finphone.getText().toString();

        if (finoccu.isEmpty()){
            finoccup.setError("Ocupación");
            Toast.makeText(finitura.this, "Ocupación", Toast.LENGTH_SHORT).show();
            return;
        }
        if (finpo.isEmpty()){
            finpow.setError("No puede estar vacía");
            Toast.makeText(finitura.this, "No puede estar vacía", Toast.LENGTH_SHORT).show();
            return;
        }
        if (finincom.isEmpty()){
            finincome.setError("No puede estar vacía");
            Toast.makeText(finitura.this, "No puede estar vacía", Toast.LENGTH_SHORT).show();
            return;
        }
        if (finidn.isEmpty()){
            finidno.setError("No puede estar vacía");
            Toast.makeText(finitura.this, "No puede estar vacía", Toast.LENGTH_SHORT).show();
            return;
        }
        if (finidn.length() < 7 ){
            finidno.setError("Inválida");
            Toast.makeText(finitura.this, "Inválida", Toast.LENGTH_SHORT).show();
            return;
        }
        if (finidn.length() > 10 ){
            finidno.setError("Inválida");
            Toast.makeText(finitura.this, "Inválida", Toast.LENGTH_SHORT).show();
            return;
        }
        if (finkee.isEmpty()){
            finkeen.setError("Nombre");
            Toast.makeText(finitura.this, "Nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (finrelatio.isEmpty()){
            finrelation.setError("Relación");
            Toast.makeText(finitura.this, "Relación", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phon.isEmpty()){
            finphone.setError("Número de teléfono");
            Toast.makeText(finitura.this, "Número de teléfono", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phon.length() < 7 ){
            finphone. setError("Numero de telefono invalido");
            Toast.makeText(finitura.this, "Numero de telefono invalido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phon.length() > 13 ){
            finphone.setError("Numero de telefono invalido");
            Toast.makeText(finitura.this, "Numero de telefono invalido", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("occupation", finoccu);
        editor.putString("workplace", finpo);
        editor.putString("income", finincom);
        editor.putString("IDno", finidn);
        editor.putString("keen_name", finkee);
        editor.putString("relationship", finrelatio);
        editor.putString("keen_phone", phon);
        editor.commit();


        progressDialog = new ProgressDialog(finitura.this);
        progressDialog.setMessage("Guardando detalles...");
        progressDialog.setProgressStyle(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(4000);
                    Intent intent = new Intent(finitura.this, grenze_witz.class);
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