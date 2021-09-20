package com.example.apputviklingmappe1_s344106_s344082;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Oppsummering extends AppCompatActivity {

    private TextView oppsummeringInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oppsummering);

        oppsummeringInfo = (TextView) findViewById(R.id.oppsummeringInfo);
        renderInfo();
        tilbakeTilMain();
    }

    private void renderInfo(){
        int antallRiktig = StartSpill.antallRiktig;
        int antallFeil = StartSpill.antallFeil;
        SeStatistikk.totaltRiktig += antallRiktig;
        SeStatistikk.totaltFeil += antallFeil;
        String infoTekst = getString(R.string.oppsummeringInfo, antallRiktig, antallFeil);
        oppsummeringInfo.setText(infoTekst);
    }

    private void tilbakeTilMain(){
        Button tilbakeTilMain = (Button)findViewById(R.id.button_tilbake_til_main);

        tilbakeTilMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}