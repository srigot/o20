package com.rigot.macavealpha.ref;

/**
 * Created by Seb on 11/11/13.
 */
public enum RefCategorie {
    VINS("Vins"),
    SPIRITUEUX("Spiritueux"),
    CHAMPAGNES("Champagnes"),
    BIERES("Bières");

    /**
     * Libelle de la categorie
     */
    private String libelle;

    private RefCategorie(final String pLibelle) {
        libelle = pLibelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
