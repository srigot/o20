package com.rigot.macavealpha;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.rigot.macavealpha.metier.GestionCave;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Classe principale de l'application
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.content)
    View content;

    ListeVinFragment listeVinFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolBar();
        setupDrawerLayout();

        listeVinFragment = new ListeVinFragment();
        chargerFragmentListe();

        // Initialisation de la cave
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new ChargementCaveTask().execute();
        } else {
            afficherMessage("No network connection available.");
        }
    }

    /**
     * Initialisation de la barre de navigation
     */
    private void setupDrawerLayout() {
        NavigationView nv = (NavigationView) findViewById(R.id.navigation);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                afficherMessage(menuItem.getTitle() + " pressed");
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

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
                .replace(R.id.container, listeVinFragment)
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

    public void afficherMessage(String message) {
        Snackbar.make(content, message, Snackbar.LENGTH_LONG).show();
    }

    public class ChargementCaveTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String msg;
            try {
                GestionCave.getInstance().ChargerCave();
                msg = "Nombre de vins = " + GestionCave.getInstance().getListeVins().size();
            } catch (IOException e) {
                msg = "Erreur : " + e.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            afficherMessage(msg);
            listeVinFragment.updateListe(GestionCave.getInstance().getListeVins());
        }
    }
}
