package com.example.apputviklingmappe1_s344106_s344082;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class StartSpill extends AppCompatActivity {

    String[] regnestykkerArray;
    String[] svarArray;
    int indexInArray;
    int antallFeil;
    int antallRiktig;
    int currentSvar = -1;
    int antallRegnestykkerBesvart;
    private TextView tvAntallRiktig;
    private TextView tvAntallFeil;
    private TextView tvRegnestykke;
    private TextView tvSvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_spill);

        tvAntallRiktig = (TextView) findViewById(R.id.antallRiktig);
        tvAntallFeil = (TextView) findViewById(R.id.antallFeil);
        tvRegnestykke = (TextView) findViewById(R.id.regnestykke);
        tvSvar = (TextView) findViewById(R.id.svar);

        Resources res = getResources();
        regnestykkerArray = res.getStringArray(R.array.regnestykker);
        svarArray = res.getStringArray(R.array.svar);
        inputVerdi();
        slettSiste();
        sendSvar();
        hentRegnestykke();
        renderRiktigOgFeil();
    }

    private void renderRiktigOgFeil(){
        String tekstRiktig = getString(R.string.antallRiktig, antallRiktig);
        String tekstFeil = getString(R.string.antallFeil, antallFeil);
        tvAntallRiktig.setText(tekstRiktig);
        tvAntallFeil.setText(tekstFeil);
    }

    private void inputVerdi(){
        final Button button_1 = (Button) findViewById(R.id.button_1);
        final Button button_2 = (Button) findViewById(R.id.button_2);
        final Button button_3 = (Button) findViewById(R.id.button_3);
        final Button button_4 = (Button) findViewById(R.id.button_4);
        final Button button_5 = (Button) findViewById(R.id.button_5);
        final Button button_6 = (Button) findViewById(R.id.button_6);
        final Button button_7 = (Button) findViewById(R.id.button_7);
        final Button button_8 = (Button) findViewById(R.id.button_8);
        final Button button_9 = (Button) findViewById(R.id.button_9);
        final Button button_0 = (Button) findViewById(R.id.button_0);

        button_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(1);
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(2);
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(3);
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(4);
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(5);
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(6);
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(7);
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(8);
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(9);
            }
        });

        button_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                oppdaterSvar(0);
            }
        });
    }

    private void oppdaterSvar(int i){
        if (currentSvar == 0 || currentSvar == -1){
            currentSvar = i;
        } else {
            currentSvar = currentSvar * 10 + i;
        }
        String strSvar = Integer.toString(currentSvar);
        tvSvar.setText(strSvar);
    }

    private void slettSiste(){
        final ImageButton slett = (ImageButton) findViewById(R.id.slett);

        slett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String gammelTekst = tvSvar.getText().toString();
                int svarLengde = String.valueOf(currentSvar).length();

                if (currentSvar < 0){
                    return;
                }

                String nyTekst = gammelTekst.substring(0, gammelTekst.length() - 1);

                if (gammelTekst.length() >= 2){
                    currentSvar = Integer.parseInt(nyTekst);
                } else {
                    currentSvar = -1;
                }

                tvSvar.setText(nyTekst);
            }
        });
    }

    private void sendSvar(){
        final Button svar = (Button) findViewById(R.id.button_sendsvar);

        svar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                antallRegnestykkerBesvart++;
                sjekkSvar();
                regnestykkerArray = fjernFraArray(regnestykkerArray, indexInArray);
                svarArray = fjernFraArray(svarArray, indexInArray);
                if (Preferanser.currentPreferanse == antallRegnestykkerBesvart){
                    tilOppsummering();
                    return;
                }
                String setTekst = getString(R.string.svar);
                tvSvar.setText(setTekst);
                currentSvar = -1;
                hentRegnestykke();
            }
        });
    }

    private void sjekkSvar(){
        int svar = Integer.parseInt(svarArray[indexInArray]);
        System.out.println("svar: " + svar);
        System.out.println("currentSvar: " + currentSvar);

        if (svar == currentSvar){
            oppdaterAntallRikitgOgFeil(tvAntallRiktig, "riktig");
        } else {
            oppdaterAntallRikitgOgFeil(tvAntallFeil, "feil");
        }
    }

    private void oppdaterAntallRikitgOgFeil(TextView tv, String check){
        System.out.println(tv.getId());
        if (check.equals("riktig")){
            antallRiktig ++;
            String tekstRiktig = getString(R.string.antallRiktig, antallRiktig);
            tv.setText(tekstRiktig);
        } else {
            antallFeil ++;
            String tekstFeil = getString(R.string.antallFeil, antallFeil);
            tv.setText(tekstFeil);
        }

    }

    private void hentRegnestykke(){
        Random r = new Random();
        int randomRegnestykke = r.nextInt(regnestykkerArray.length);
        tvRegnestykke.setText(regnestykkerArray[randomRegnestykke]);
        indexInArray = randomRegnestykke;
    }

    private String[] fjernFraArray(String[] arr, int fjernIndex){
        String[] tmpArray = new String[arr.length - 1];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {

            if (i == fjernIndex) {
                continue;
            }
            tmpArray[index] = arr[i];
            index ++;
        }
        return tmpArray;
    }

    private void tilOppsummering(){
        startActivity(new Intent(StartSpill.this, Oppsummering.class));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("antallRiktig", tvAntallRiktig.getText().toString());
        outState.putString("antallFeil", tvAntallFeil.getText().toString());
        outState.putString("regnestykke", tvRegnestykke.getText().toString());
        outState.putString("svar", tvSvar.getText().toString());
        outState.putInt("currentSvar", currentSvar);
        outState.putInt("indexInArray", indexInArray);
        outState.putInt("antallRegnestykkerBesvart", antallRegnestykkerBesvart);
        outState.putInt("intAntallFeil", antallFeil);
        outState.putInt("intAntallRiktig", antallRiktig);
        outState.putStringArray("regnestykkerArray", regnestykkerArray);
        outState.putStringArray("svarArray", svarArray);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvAntallRiktig.setText(savedInstanceState.getString("antallRiktig"));
        tvAntallFeil.setText(savedInstanceState.getString("antallFeil"));
        tvRegnestykke.setText(savedInstanceState.getString("regnestykke"));
        tvSvar.setText(savedInstanceState.getString("svar"));
        currentSvar = savedInstanceState.getInt("currentSvar");
        indexInArray = savedInstanceState.getInt("indexInArray");
        antallRegnestykkerBesvart = savedInstanceState.getInt("antallRegnestykkerBesvart");
        antallFeil = savedInstanceState.getInt("intAntallFeil");
        antallRiktig = savedInstanceState.getInt("intAntallRiktig");
        regnestykkerArray = savedInstanceState.getStringArray("regnestykkerArray");
        svarArray = savedInstanceState.getStringArray("svarArray");
    }
}