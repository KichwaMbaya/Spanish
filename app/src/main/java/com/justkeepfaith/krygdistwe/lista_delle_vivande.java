package com.justkeepfaith.krygdistwe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class lista_delle_vivande extends AppCompatActivity {

    TextView MoreProf, MoreEmail, MoreTerms, MorePrivacy, Morelgtbutton;
    SharedPreferences sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_delle_vivande);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int hight = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (hight * 1));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.RIGHT;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);



        MoreProf = (TextView) findViewById(R.id.MoreProf);
        MoreEmail = (TextView) findViewById(R.id.MoreEmail);
        MoreTerms = (TextView) findViewById(R.id.MoreTerms);
        MorePrivacy = (TextView) findViewById(R.id.MorePrivacy);
        Morelgtbutton = (TextView) findViewById(R.id.Morelgtbutton);


        Morelgtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Conectivity.isConnectingToInternet(lista_delle_vivande.this)) {
                    Toast.makeText(lista_delle_vivande.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
                    return;
                }
                sharedPreferences1 = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                Integer logged_out = 1;
                editor.putInt("logged", logged_out);
                editor.commit();

                Toast.makeText(lista_delle_vivande.this, "Desconectado", Toast.LENGTH_SHORT).show();

                //restart app
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        });
        MoreProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(lista_delle_vivande.this, profilo.class);
                startActivity(intent);
            }
        });
        MoreEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Conectivity.isConnectingToInternet(lista_delle_vivande.this)) {
                    Toast.makeText(lista_delle_vivande.this, "Por favor revise su conexion a internet", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(lista_delle_vivande.this, Contattaci.class);
                startActivity(intent);
            }
        });
        MoreTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(lista_delle_vivande.this, termini_condizioni.class);
                startActivity(intent);
            }
        });
        MorePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(lista_delle_vivande.this, politica_sulla_riservatezza.class);
                startActivity(intent);
            }
        });
    }
}