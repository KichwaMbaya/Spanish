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

import androidx.appcompat.app.AppCompatActivity;

public class notifica extends AppCompatActivity {

    TextView ntftit;
    SharedPreferences sharedPreferences;
    String notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifica);

        ntftit = (TextView) findViewById(R.id.ntftit);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int hight = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (hight*1));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.LEFT;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        notif = sharedPreferences.getString("notification", "");

        ntftit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(notifica.this, striscia_pagina.class);
                startActivity(intent);
                finish();
            }
        });
        ntftit.setText(notif);
    }
}