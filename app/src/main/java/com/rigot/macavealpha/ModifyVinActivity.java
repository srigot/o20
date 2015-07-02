package com.rigot.macavealpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.rigot.macavealpha.metier.GestionCave;
import com.rigot.macavealpha.metier.Vin;
import com.rigot.macavealpha.ref.RefAppellation;
import com.rigot.macavealpha.ref.RefCategorie;
import com.rigot.macavealpha.ref.RefCouleur;
import com.rigot.macavealpha.util.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ModifyVinActivity extends Activity {
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

        init_spinnerAppellation();
        init_spinnerCategorie();
        init_spinnerCouleur();

        chargerVin();
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
            spAppellation.setSelection(appellationAdapter.getPosition(vin.getAppellation()));
            spCategorie.setSelection(categorieAdapter.getPosition(vin.getCategorie()));
            spCouleur.setSelection(couleurAdapter.getPosition(vin.getCouleur()));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_vin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.ok) {
            validerDetail();
        }

        return super.onOptionsItemSelected(item);
    }

    public void validerDetail() {
        if (vin == null) {
            vin = new Vin();
        }

        remplirChamps();

        if (vin.getId() == null) {
            GestionCave.getInstance().AjouterVin(vin);
        } else {
            GestionCave.getInstance().ModifierVin(vin);
        }
        finish();
    }

    private void remplirChamps() {
        // TODO Verifier champs obligatoire

        vin.setNom(etNom.getText().toString());
        vin.setAnnee(StringUtil.StringToInteger(etAnnee.getText().toString()));
        vin.setAppellation((RefAppellation) spAppellation.getSelectedItem());
        vin.setCategorie((RefCategorie) spCategorie.getSelectedItem());
        vin.setCouleur((RefCouleur) spCouleur.getSelectedItem());
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
}
