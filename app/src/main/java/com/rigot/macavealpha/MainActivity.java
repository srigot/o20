package com.rigot.macavealpha;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.rigot.macavealpha.metier.GestionCave;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Classe principale de l'application
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolBar();
        setupDrawerLayout();

        chargerFragmentListe();
        // Initialisation de la cave
        GestionCave.getInstance().ChargerCave(this);
    }

    /**
     * Initialisation de la barre de navigation
     */
    private void setupDrawerLayout() {
        // TODO Ajouter la prise en compte du choix dans le menu de navigation
    }

    private void initToolBar() {
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void chargerFragmentListe() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new ListeVinFragment())
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fabAjouter)
    public void onClickFabAjouter(View v) {
        // Lancer l'activite "Detail Vin"
        Intent intent = new Intent(this, ModifyVinActivity.class);
        startActivity(intent);
    }

}
