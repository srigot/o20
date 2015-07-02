package com.rigot.macavealpha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rigot.macavealpha.metier.GestionCave;
import com.rigot.macavealpha.metier.Vin;


public class DetailVinActivity extends Activity {

    /**
     * Identifiant du vin a afficher
     */
    public static final String ID = "com.rigot.macavealpha.IDENTIFIANT";

    private static final int ID_UNKNOWN = -1;

    private Vin vin = null;

    private int idVin = ID_UNKNOWN;

    private TextView tvNom = null;
    private TextView tvAnnee = null;
    private TextView tvCouleur = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vin);

        Intent intent = getIntent();
        idVin = intent.getIntExtra(ID, ID_UNKNOWN);
        if (idVin != ID_UNKNOWN) {
            vin = GestionCave.getInstance().getVinPosition(idVin);
        }

        tvNom = (TextView) findViewById(R.id.tvNom);
        tvAnnee = (TextView) findViewById(R.id.tvAnnee);
        tvCouleur = (TextView) findViewById(R.id.tvCouleur);

        chargerVin();
    }


    private void chargerVin() {
        if (vin != null) {
            tvNom.setText(vin.getNom());
            tvAnnee.setText(vin.getAnnee().toString());
            tvCouleur.setText(vin.getCouleur().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_vin, menu);
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

        if (id == R.id.modifier) {
            // Lancer l'activite Modifiy
            Intent intent = new Intent(this, ModifyVinActivity.class);
            intent.putExtra(ModifyVinActivity.ID, idVin);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
