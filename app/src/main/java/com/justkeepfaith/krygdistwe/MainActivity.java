package com.justkeepfaith.krygdistwe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mainlogin, mainregist;
    SharedPreferences sharedPreferences;
    String reg_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainlogin = findViewById(R.id.mainlogin);
        mainregist = findViewById(R.id.mainregist);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        Integer status = sharedPreferences.getInt("logged", 0);
        reg_email = sharedPreferences.getString("Email", "");

        if (status == 2) {

            if (reg_email.isEmpty()) {

                /*Intent intent = new Intent(MainActivity.this, in_progress.class);
                startActivity(intent);
                finish();*/
            } else {

                Intent intent = new Intent(MainActivity.this, finitura.class);
                startActivity(intent);
                finish();
            }
        }

        mainlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, accesso.class);//here
                startActivity(intent);
            }
        });

        mainregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registrati.class);
                startActivity(intent);
            }
        });
    }
}