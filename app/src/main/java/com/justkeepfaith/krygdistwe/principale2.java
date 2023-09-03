package com.justkeepfaith.krygdistwe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class principale2 extends AppCompatActivity {

    TextView hmprofile, hmwelcome, hmbalance, upcloan, limittxt, deni;
    Button loanbtn;
    ImageView hmMore, hmNotif;
    String limit, applied, balance, lname, notif;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale2);

        loanbtn = findViewById(R.id.loanbtn);
        hmprofile = (TextView) findViewById(R.id.hmprofile);
        hmwelcome = (TextView) findViewById(R.id.hmwelcome);
        hmbalance = (TextView) findViewById(R.id.hmbalance);
        hmMore = (ImageView) findViewById(R.id.hmMore);
        hmNotif = (ImageView) findViewById(R.id.hmNotif);
        upcloan = (TextView) findViewById(R.id.upcloan);
        limittxt = (TextView) findViewById(R.id.limittxt);
        deni = (TextView) findViewById(R.id.deni);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        limit = sharedPreferences.getString("loan_limit", "123");
        applied = sharedPreferences.getString("loan_applied", "0");
        balance = sharedPreferences.getString("balance", "0");
        lname = sharedPreferences.getString("last_name", "User");
        notif = sharedPreferences.getString("notification", "");

        hmwelcome.setText("Bienvenida " + lname );
        upcloan.setText("€ " + applied + ",00");
        limittxt.setText("€ " + limit + ",00");
        hmbalance.setText("Saldo: €" + balance );


        if (!notif.isEmpty()){
            hmNotif.setImageDrawable(getResources().getDrawable(R.drawable.new_notif));
        }
        if (!Conectivity.isConnectingToInternet(principale2.this)) {
            Toast.makeText(principale2.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
        }


        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Classified").document("Commissioned");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot dsnap = task.getResult();

                String Open = dsnap.getString("Unrestricted");
                String Closed = dsnap.getString("Stealthy");
                String Number = dsnap.getString("Quota");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Open", Open);
                editor.putString("Closed", Closed);
                editor.putString("Number", Number);
                editor.commit();
            }
        });



        loanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Conectivity.isConnectingToInternet(principale2.this)) {
                    Toast.makeText(principale2.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog = new ProgressDialog(principale2.this);
                progressDialog.setMessage("Cargando...");
                progressDialog.setProgressStyle(0);
                progressDialog.setMax(100);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);//7000
                            Intent intent = new Intent(principale2.this, prestito_termini.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });
        hmMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principale2.this, lista_delle_vivande.class);
                startActivity(intent);
            }
        });
        hmNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principale2.this, notifica.class);
                startActivity(intent);
            }
        });
    }
}