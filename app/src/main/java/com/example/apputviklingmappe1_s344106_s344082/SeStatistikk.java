package com.example.apputviklingmappe1_s344106_s344082;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SeStatistikk extends AppCompatActivity {
    static int totaltRiktig;
    static int totaltFeil;
    private TextView tvStatistikkRiktig;
    private TextView tvStatistikkFeil;
    private TextView tvStatistikkTekstRiktig;
    private TextView tvStatistikkTekstFeil;
    private TextView tvOverskrift;
    private TextView tvSlettStatistikk;
    private String popupMelding;
    private String popupJa;
    private String popupNei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.se_statistikk);
        tvStatistikkRiktig = (TextView) findViewById(R.id.statistikkRiktig);
        tvStatistikkFeil = (TextView ) findViewById(R.id.statistikkFeil);
        tvOverskrift = (TextView ) findViewById(R.id.overskriftStatistikk);
        tvSlettStatistikk = (TextView ) findViewById(R.id.slettStatistikk);
        tvStatistikkTekstRiktig = (TextView ) findViewById(R.id.statistikkTekstRiktig);
        tvStatistikkTekstFeil = (TextView ) findViewById(R.id.statistikkTekstFeil);
        popupMelding = SeStatistikk.this.getString(R.string.popupMeldingStatistikk);
        popupJa = SeStatistikk.this.getString(R.string.popupPos);
        popupNei = SeStatistikk.this.getString(R.string.popupNeg);
        renderStatistikk();
        resetStatistikk();
    }

    private void renderStatistikk() {
        String txtStatistikkRiktig = Integer.toString(totaltRiktig);
        String txtStatistikkFeil = Integer.toString(totaltFeil);
        tvStatistikkRiktig.setText(txtStatistikkRiktig);
        tvStatistikkRiktig.setTextColor(Color.GREEN);
        tvStatistikkFeil.setText(txtStatistikkFeil);
        tvStatistikkFeil.setTextColor(Color.RED);
    }

    private void resetStatistikk() {
        Button resetStatistikk =  (Button)findViewById(R.id.slettStatistikk);

        resetStatistikk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog popup = new AlertDialog.Builder(SeStatistikk.this)
                    .setMessage(popupMelding)
                    .setPositiveButton(popupJa, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            totaltRiktig = 0;
                            totaltFeil = 0;
                            dialogInterface.cancel();
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(popupNei, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Overskrift", tvOverskrift.getText().toString());
        outState.putString("statistikkRiktig", tvStatistikkRiktig.getText().toString());
        outState.putString("statistikkFeil", tvStatistikkFeil.getText().toString());
        outState.putString("slettStatistikk", tvSlettStatistikk.getText().toString());
        outState.putString("statistikkTekstRiktig", tvStatistikkTekstRiktig.getText().toString());
        outState.putString("statistikkTekstFeil", tvStatistikkTekstFeil.getText().toString());
        outState.putString("popupMelding", popupMelding);
        outState.putString("popupJa", popupJa);
        outState.putString("popupNei", popupNei);
        super.onSaveInstanceState(outState);
}

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvOverskrift.setText(savedInstanceState.getString("Overskrift"));
        tvStatistikkRiktig.setText(savedInstanceState.getString("statistikkRiktig"));
        tvStatistikkFeil.setText(savedInstanceState.getString("statistikkFeil"));
        tvSlettStatistikk.setText(savedInstanceState.getString("slettStatistikk"));
        tvStatistikkTekstRiktig.setText(savedInstanceState.getString("statistikkTekstRiktig"));
        tvStatistikkTekstFeil.setText(savedInstanceState.getString("statistikkTekstFeil"));
        popupMelding = savedInstanceState.getString("popupMelding", popupMelding);
        popupJa = savedInstanceState.getString("popupJa", popupJa);
        popupNei = savedInstanceState.getString("popupNei", popupNei);
    }
}