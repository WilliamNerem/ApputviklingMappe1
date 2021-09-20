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
    private TextView statistikkRiktig;
    private TextView statistikkFeil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.se_statistikk);
        statistikkRiktig = (TextView) findViewById(R.id.statistikkRiktig);
        statistikkFeil = (TextView ) findViewById(R.id.statistikkFeil);
        renderStatistikk();
        resetStatistikk();
    }

    private void renderStatistikk() {
        String txtStatistikkRiktig = Integer.toString(totaltRiktig);
        String txtStatistikkFeil = Integer.toString(totaltFeil);
        statistikkRiktig.setText(txtStatistikkRiktig);
        statistikkRiktig.setTextColor(Color.GREEN);
        statistikkFeil.setText(txtStatistikkFeil);
        statistikkFeil.setTextColor(Color.RED);
    }

    private void resetStatistikk() {
        Button resetStatistikk =  (Button)findViewById(R.id.slettStatistikk);

        resetStatistikk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog popup = new AlertDialog.Builder(SeStatistikk.this)
                        .setMessage(SeStatistikk.this.getString(R.string.popupMeldingStatistikk))
                        .setPositiveButton(SeStatistikk.this.getString(R.string.popupPos), new DialogInterface.OnClickListener() {
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
                        .setNegativeButton(SeStatistikk.this.getString(R.string.popupNeg), new DialogInterface.OnClickListener() {
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
    super.onSaveInstanceState(outState);
}

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}