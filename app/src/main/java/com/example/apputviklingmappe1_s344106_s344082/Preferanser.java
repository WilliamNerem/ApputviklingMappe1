package com.example.apputviklingmappe1_s344106_s344082;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Preferanser extends AppCompatActivity {

    static int currentPreferanse = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferanser);

        endrePreferanse();
    }

    public void endrePreferanse(){
        Button button5 = (Button)findViewById(R.id.button_preferanser_5);
        Button button10 = (Button)findViewById(R.id.button_preferanser_10);
        Button button15 = (Button)findViewById(R.id.button_preferanser_15);
        endrePreferanseTekst();

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPreferanse = 5;
                endrePreferanseTekst();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPreferanse = 10;
                endrePreferanseTekst();
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPreferanse = 15;
                endrePreferanseTekst();
            }
        });
    }

     public void endrePreferanseTekst(){
         TextView preferanseTekst = (TextView) findViewById(R.id.current_preferanse);
         String tekst = getString(R.string.currentPreferanse, currentPreferanse);
         preferanseTekst.setText(tekst);
     }


}