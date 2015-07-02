package com.rigot.macavealpha.ref;

import com.rigot.macavealpha.R;


/**
 * Created by Seb on 11/11/13.
 */
public enum RefCouleur {
    ROUGE("Rouge", R.color.vinRouge),
    BLANC("Blanc", R.color.vinBlanc),
    ROSE("Rosé", R.color.vinRose);

    private String libelle;
    private int couleur;

    private RefCouleur(final String pLibelle, final int pCouleur) {
        libelle = pLibelle;
        couleur = pCouleur;
    }

    @Override
    public String toString() {
        return libelle;
    }

    public int getCouleur() {
        return couleur;
    }
}
