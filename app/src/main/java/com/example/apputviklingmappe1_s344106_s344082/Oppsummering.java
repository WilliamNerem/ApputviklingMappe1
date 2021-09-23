package com.example.apputviklingmappe1_s344106_s344082;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Oppsummering extends AppCompatActivity {

    private TextView tvOppsummeringInfo;
    private TextView tvOverskrift;
    private TextView tvTilbakeTilMain;
    private int antallRiktig;
    private int antallFeil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oppsummering);

        tvOppsummeringInfo = (TextView) findViewById(R.id.oppsummeringInfo);
        tvOverskrift = (TextView) findViewById(R.id.overskriftOppsummering);
        tvTilbakeTilMain = (TextView) findViewById(R.id.button_tilbake_til_main);
        renderInfo();
        tilbakeTilMain();
    }

    private void renderInfo(){
        antallRiktig = StartSpill.antallRiktig;
        antallFeil = StartSpill.antallFeil;
        String infoTekst = getString(R.string.oppsummeringInfo, antallRiktig, antallFeil);
        tvOppsummeringInfo.setText(infoTekst);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Overskrift", tvOverskrift.getText().toString());
        outState.putString("OppsummeringInfo", tvOppsummeringInfo.getText().toString());
        outState.putString("TilbakeTilMain", tvTilbakeTilMain.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvOverskrift.setText(savedInstanceState.getString("Overskrift"));
        tvOppsummeringInfo.setText(savedInstanceState.getString("OppsummeringInfo"));
        tvTilbakeTilMain.setText(savedInstanceState.getString("TilbakeTilMain"));
    }

    @Override
    public void onBackPressed()
    {
        SeStatistikk.antallRiktig = antallRiktig;
        SeStatistikk.antallFeil = antallFeil;
        super.onBackPressed();
    }
}