package com.example.apputviklingmappe1_s344106_s344082;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class StartSpill extends AppCompatActivity {

    int currentSvar = -1;
    String[] regnestykker;
    String[] svarArray;
    int indexInArray;
    int antallRegnestykkerBesvart;
    static int antallFeil;
    static int antallRiktig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_spill);

        resetVerdier();
        Resources res = getResources();
        regnestykker = res.getStringArray(R.array.regnestykker);
        svarArray = res.getStringArray(R.array.svar);
        inputVerdi();
        slettSiste();
        sendSvar();
        hentRegnestykke();
        renderRiktigOgFeil();
    }

    private void resetVerdier(){
        antallRiktig = 0;
        antallFeil = 0;
        antallRegnestykkerBesvart = 0;
    }

    private void renderRiktigOgFeil(){
        TextView riktig = (TextView) findViewById(R.id.antallRiktig);
        TextView feil = (TextView) findViewById(R.id.antallFeil);
        String tekstRiktig = getString(R.string.antallRiktig, antallRiktig);
        String tekstFeil = getString(R.string.antallFeil, antallFeil);
        riktig.setText(tekstRiktig);
        feil.setText(tekstFeil);
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
        TextView tekst = (TextView) findViewById(R.id.svar);

        button_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("1");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("2");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("3");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("4");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("5");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("6");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("7");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("8");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("9");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });

        button_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tekst.append("0");
                currentSvar = Integer.parseInt(tekst.getText().toString());
            }
        });
    }

    private void slettSiste(){
        final ImageButton slett = (ImageButton) findViewById(R.id.slett);

        slett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tekst = (TextView) findViewById(R.id.svar);
                String gammelTekst = tekst.getText().toString();
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

                tekst.setText(nyTekst);
            }
        });
    }

    private void sendSvar(){
        final Button svar = (Button) findViewById(R.id.button_sendsvar);

        svar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                antallRegnestykkerBesvart++;
                sjekkSvar();
                regnestykker = fjernFraArray(regnestykker, indexInArray);
                svarArray = fjernFraArray(svarArray, indexInArray);
                if (Preferanser.currentPreferanse == antallRegnestykkerBesvart){
                    tilOppsummering();
                    return;
                }
                TextView tekst = (TextView) findViewById(R.id.svar);
                String setTekst = getString(R.string.svar);
                tekst.setText(setTekst);
                hentRegnestykke();
            }
        });
    }

    private void sjekkSvar(){
        TextView riktig = (TextView) findViewById(R.id.antallRiktig);
        TextView feil = (TextView) findViewById(R.id.antallFeil);
        int svar = Integer.parseInt(svarArray[indexInArray]);

        if (svar == currentSvar){
            oppdaterSvar(riktig, "riktig");
        } else {
            oppdaterSvar(feil, "feil");
        }
    }

    private void oppdaterSvar(TextView tv, String check){
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
        TextView tekst = (TextView) findViewById(R.id.regnestykke);
        Random r = new Random();
        int randomRegnestykke = r.nextInt(regnestykker.length);
        tekst.setText(regnestykker[randomRegnestykke]);
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
}