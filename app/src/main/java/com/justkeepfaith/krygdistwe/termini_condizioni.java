package com.justkeepfaith.krygdistwe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class termini_condizioni extends AppCompatActivity {

    Button okbuttont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termini_condizioni);

        okbuttont = findViewById(R.id.okbuttont);

        okbuttont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}