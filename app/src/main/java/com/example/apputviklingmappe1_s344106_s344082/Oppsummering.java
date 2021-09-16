package com.example.apputviklingmappe1_s344106_s344082;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Oppsummering extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.se_statistikk);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Oppsummering.this, MainActivity.class));
    }
}