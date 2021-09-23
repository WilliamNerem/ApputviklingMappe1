package com.example.apputviklingmappe1_s344106_s344082;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SeStatistikk extends AppCompatActivity {
    static int antallRiktig;
    static int antallFeil;
    private int totaltRiktig;
    private int totaltFeil;
    private TextView tvStatistikkRiktig;
    private TextView tvStatistikkFeil;
    private TextView tvStatistikkTekstRiktig;
    private TextView tvStatistikkTekstFeil;
    private TextView tvOverskrift;
    private TextView tvSlettStatistikk;
    private TextView tvSvarProsent;
    private TextView tvSvarProsentTekst;
    private String popupMelding;
    private String popupJa;
    private String popupNei;

    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

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
        tvSvarProsent = (TextView ) findViewById(R.id.svarProsent);
        tvSvarProsentTekst = (TextView ) findViewById(R.id.svarProsentTekst);
        popupMelding = SeStatistikk.this.getString(R.string.popupMeldingStatistikk);
        popupJa = SeStatistikk.this.getString(R.string.popupPos);
        popupNei = SeStatistikk.this.getString(R.string.popupNeg);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        totaltRiktig = antallRiktig + preferences.getInt("totaltRiktig", 0);
        totaltFeil = antallFeil + preferences.getInt("totaltFeil", 0);
        editor.putInt("totaltRiktig", totaltRiktig);
        editor.putInt("totaltFeil", totaltFeil);
        editor.commit();
        renderStatistikk();
        resetStatistikk();
    }

    private void renderStatistikk() {
        String txtStatistikkRiktig = Integer.toString(preferences.getInt("totaltRiktig", 0));
        String txtStatistikkFeil = Integer.toString(preferences.getInt("totaltFeil", 0));
        String prosentStr;
        float prosent;
        if ((totaltFeil + totaltRiktig) != 0) {
            prosent = (totaltRiktig * 100.0f) / (totaltRiktig + totaltFeil);
            DecimalFormat df = new DecimalFormat("#.#");
            prosentStr = String.valueOf(df.format(prosent)) + "%";
        } else {
            prosentStr = "0%";
        }
        tvStatistikkRiktig.setText(txtStatistikkRiktig);
        tvStatistikkRiktig.setTextColor(Color.GREEN);
        tvStatistikkFeil.setText(txtStatistikkFeil);
        tvStatistikkFeil.setTextColor(Color.RED);
        tvSvarProsent.setText(prosentStr);
    }

    private void resetStatistikk() {
        Button resetStatistikk =  (Button)findViewById(R.id.slettStatistikk);

        resetStatistikk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SeStatistikk.this)
                    .setMessage(popupMelding)
                    .setPositiveButton(popupJa, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            antallFeil = 0;
                            antallRiktig = 0;
                            editor.putInt("totaltRiktig", 0);
                            editor.putInt("totaltFeil", 0);
                            editor.commit();
                            dialogInterface.cancel();
                            reRender();
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

    private void reRender(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Overskrift", tvOverskrift.getText().toString());
        outState.putString("statistikkRiktig", tvStatistikkRiktig.getText().toString());
        outState.putString("statistikkFeil", tvStatistikkFeil.getText().toString());
        outState.putString("slettStatistikk", tvSlettStatistikk.getText().toString());
        outState.putString("statistikkTekstRiktig", tvStatistikkTekstRiktig.getText().toString());
        outState.putString("statistikkTekstFeil", tvStatistikkTekstFeil.getText().toString());
        outState.putString("tvSvarProsent", tvSvarProsent.getText().toString());
        outState.putString("tvSvarProsentTekst", tvSvarProsentTekst.getText().toString());
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
        tvSvarProsent.setText(savedInstanceState.getString("tvSvarProsent"));
        tvSvarProsentTekst.setText(savedInstanceState.getString("tvSvarProsentTekst"));
        popupMelding = savedInstanceState.getString("popupMelding", popupMelding);
        popupJa = savedInstanceState.getString("popupJa", popupJa);
        popupNei = savedInstanceState.getString("popupNei", popupNei);
    }
}