package com.example.firefly.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.firefly.R;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent landingPage = new Intent(MainActivity.this, ListaProyectos.class);
                startActivity(landingPage);
                finish();
            }
        },SPLASH_TIME_OUT);

        TextView tituloLanding = findViewById(R.id.titulo_landing_1);
        tituloLanding.setLetterSpacing((float) 1);

        TextView subtitleLanding = findViewById(R.id.subtitle_landing);
        subtitleLanding.setLetterSpacing((float)0.5);

    }


}
