package com.example.apputviklingmappe1_s344106_s344082;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvOverskrift;
    private Button tvButtonStartSpill;
    private Button tvButtonStatistikk;
    private Button tvButtonPreferanser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOverskrift = (TextView) findViewById(R.id.overskriftMain);
        tvButtonStartSpill = (Button) findViewById(R.id.button_start);
        tvButtonStatistikk = (Button) findViewById(R.id.button_statistikk);
        tvButtonPreferanser = (Button) findViewById(R.id.button_preferanser);
        knapper();
    }

    private void knapper(){
        tvButtonStartSpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StartSpill.class));
            }

        });

        tvButtonStatistikk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SeStatistikk.class));
            }
        });
        tvButtonPreferanser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Preferanser.class));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Overskrift", tvOverskrift.getText().toString());
        outState.putString("start", tvButtonStartSpill.getText().toString());
        outState.putString("statistikk", tvButtonStatistikk.getText().toString());
        outState.putString("preferanser", tvButtonPreferanser.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvOverskrift.setText(savedInstanceState.getString("Overskrift"));
        tvButtonStartSpill.setText(savedInstanceState.getString("start"));
        tvButtonStatistikk.setText(savedInstanceState.getString("statistikk"));
        tvButtonPreferanser.setText(savedInstanceState.getString("preferanser"));
    }

    public void onResume() {
        super.onResume();

        if(Preferanser.localHasChanged) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            Preferanser.localHasChanged = false;
        }
    }

}