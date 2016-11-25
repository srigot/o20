package com.rigot.macavealpha;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.rigot.macavealpha.metier.GestionCave;
import com.rigot.macavealpha.ref.RefAppellation;
import com.rigot.macavealpha.ref.RefCategorie;
import com.rigot.macavealpha.ref.RefCouleur;
import com.rigot.macavealpha.util.StringUtil;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.rigot.cavavin.backend.vins.model.Vin;


public class ModifyVinActivity extends AppCompatActivity {
    public static final String ID = "com.rigot.macavealpha.IDENTIFIANT";

    private static final int ID_UNKNOWN = -1;
    @Bind(R.id.etNom)
    EditText etNom;
    @Bind(R.id.etAnnee)
    EditText etAnnee;
    @Bind(R.id.spAppellation)
    Spinner spAppellation;
    @Bind(R.id.spCategorie)
    Spinner spCategorie;
    @Bind(R.id.spCouleur)
    Spinner spCouleur;
    @Bind(R.id.rbNote)
    RatingBar rbNote;
    @Bind(R.id.etDebutBoire)
    EditText etDebutBoire;
    @Bind(R.id.etFinBoire)
    EditText etFinBoire;
    @Bind(R.id.etDebutTemp)
    EditText etDebutTemp;
    @Bind(R.id.etFinTemp)
    EditText etFinTemp;
    @Bind(R.id.etDegre)
    EditText etDegre;
    private Vin vin = null;
    private ArrayAdapter<RefAppellation> appellationAdapter = null;
    private ArrayAdapter<RefCategorie> categorieAdapter = null;
    private ArrayAdapter<RefCouleur> couleurAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_vin);

        Intent intent = getIntent();
        int idVin = intent.getIntExtra(ID, ID_UNKNOWN);
        if (idVin != ID_UNKNOWN) {
            vin = GestionCave.getInstance().getVinPosition(idVin);
        }

        ButterKnife.bind(this);

        init_toolbar();
        init_spinnerAppellation();
        init_spinnerCategorie();
        init_spinnerCouleur();

        chargerVin();
    }

    private void init_toolbar() {
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void init_spinnerCouleur() {
        couleurAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, RefCouleur.values());
        spCouleur.setAdapter(couleurAdapter);

    }

    private void init_spinnerCategorie() {
        categorieAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, RefCategorie.values());
        spCategorie.setAdapter(categorieAdapter);

    }

    private void init_spinnerAppellation() {
        appellationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, RefAppellation.values());
        spAppellation.setAdapter(appellationAdapter);
    }

    private void chargerVin() {
        if (vin != null) {
            etNom.setText(vin.getNom());
            etAnnee.setText(vin.getAnnee().toString());
            spAppellation.setSelection(appellationAdapter.getPosition(
                    RefAppellation.valueOf(vin.getAppellation())));
            spCategorie.setSelection(categorieAdapter.getPosition(
                    RefCategorie.valueOf(vin.getCategorie())));
            spCouleur.setSelection(couleurAdapter.getPosition(
                    RefCouleur.valueOf(vin.getCouleur())));
            rbNote.setRating(vin.getNote());
            etDebutBoire.setText(StringUtil.integerToString(vin.getDebutBoire()));
            etFinBoire.setText(StringUtil.integerToString(vin.getFinBoire()));
            etDebutTemp.setText(StringUtil.integerToString(vin.getDebutTemp()));
            etFinTemp.setText(StringUtil.integerToString(vin.getFinTemp()));
            etDegre.setText(StringUtil.floatToString(vin.getDegre()));

            // TODO
//			vin.setEstimation(estimation);
//			vin.setCepage(cepage);
//			vin.setTaille(taille);
//			vin.setCommentaires(commentaires);
        }

    }


    @OnClick(R.id.fabValiderModifier)
    public void onClickFabAjouter(View v) {
        validerDetail();
    }

    public void validerDetail() {
        if (vin == null) {
            vin = new Vin();
        }

        remplirChamps();

        // Initialisation de la cave
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new ValiderTask().execute();
        } else {
            afficherMessage("No network connection available.");
        }
    }

    private void remplirChamps() {
        // TODO Verifier champs obligatoire

        vin.setNom(etNom.getText().toString());
        vin.setAnnee(StringUtil.StringToInteger(etAnnee.getText().toString()));
        vin.setAppellation(((RefAppellation) spAppellation.getSelectedItem()).name());
        vin.setCategorie(((RefCategorie) spCategorie.getSelectedItem()).name());
        vin.setCouleur(((RefCouleur) spCouleur.getSelectedItem()).name());
        vin.setEstimation(null);
        vin.setNote(rbNote.getRating());
        vin.setCepage(null);
        vin.setTaille(null);
        vin.setDebutBoire(StringUtil.StringToInteger(etDebutBoire.getText().toString()));
        vin.setFinBoire(StringUtil.StringToInteger(etFinBoire.getText().toString()));
        vin.setCommentaires(null);
        vin.setDegre(StringUtil.StringToFloat(etDegre.getText().toString()));
        vin.setDebutTemp(StringUtil.StringToInteger(etDebutTemp.getText().toString()));
        vin.setFinTemp(StringUtil.StringToInteger(etFinTemp.getText().toString()));
    }

    public void afficherMessage(String message) {
        Toast.makeText(ModifyVinActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void finishOK() {
        this.setResult(RESULT_OK);
        finish();
    }

    private class ValiderTask extends AsyncTask<Vin, Void, String> {

        @Override
        protected String doInBackground(Vin... params) {
            String retour = null;
            try {
                if (vin.getId() == null) {
                    GestionCave.getInstance().AjouterVin(vin);
                } else {
                    GestionCave.getInstance().ModifierVin(vin);
                }
            } catch (IOException e) {
                retour = e.getMessage();
                if (retour == null) {
                    retour = "Erreur inconnue";
                }
            }
            return retour;
        }

        @Override
        protected void onPostExecute(String msg) {
            if (msg == null) {
                finishOK();
            } else {
                afficherMessage(msg);
            }
        }
    }

}
