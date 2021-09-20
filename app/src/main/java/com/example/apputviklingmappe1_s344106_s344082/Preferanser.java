package com.example.apputviklingmappe1_s344106_s344082;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class Preferanser extends AppCompatActivity {
    static int currentPreferanse = 5;
    static boolean localHasChanged = false;
    private TextView tvOverskrift;
    private TextView tvVelgSpraak;
    private TextView tvPreferanserInfo;
    private TextView tvCurrentPreferanse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferanser);

        tvOverskrift = (TextView) findViewById(R.id.overskriftPreferanser);
        tvVelgSpraak = (TextView) findViewById(R.id.velgSpraak);
        tvPreferanserInfo = (TextView) findViewById(R.id.preferanserInfo);
        tvCurrentPreferanse = (TextView) findViewById(R.id.current_preferanse);
        endrePreferanse();
        settSpråk();
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

    public void settSpråk() {
        final ImageButton button_lang_no = (ImageButton) findViewById(R.id.button_lang_no);
        final ImageButton button_lang_de = (ImageButton) findViewById(R.id.button_lang_de);
        settSpråkendring("");

        button_lang_no.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              settSpråkendring("no");
          }
      });

        button_lang_de.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             settSpråkendring("de");
          }
        });
    }
    public void settSpråkendring(String lang) {
        if (!lang.equals("")) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            localHasChanged=true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Overskrift", tvOverskrift.getText().toString());
        outState.putString("VelgSpraak", tvVelgSpraak.getText().toString());
        outState.putString("PreferanserInfo", tvPreferanserInfo.getText().toString());
        outState.putString("CurrentPreferanse", tvCurrentPreferanse.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvOverskrift.setText(savedInstanceState.getString("Overskrift"));
        tvVelgSpraak.setText(savedInstanceState.getString("VelgSpraak"));
        tvPreferanserInfo.setText(savedInstanceState.getString("PreferanserInfo"));
        tvCurrentPreferanse.setText(savedInstanceState.getString("CurrentPreferanse"));
    }
}