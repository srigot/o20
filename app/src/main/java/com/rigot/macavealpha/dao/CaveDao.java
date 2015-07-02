package com.rigot.macavealpha.dao;

import android.provider.BaseColumns;

public final class CaveDao {
    public CaveDao() {

    }

    public static abstract class VinDao implements BaseColumns {
        public static final String TABLE_NAME = "VIN";
        public static final String COL_NAME_NOM = "nom";
        public static final String COL_NAME_ANNEE = "annee";
        public static final String COL_NAME_APPELLATION = "appellation";
        public static final String COL_NAME_CATEGORIE = "categorie";
        public static final String COL_NAME_COULEUR = "couleur";
        public static final String COL_NAME_ESTIMATION = "estimation";
        public static final String COL_NAME_NOTE = "note";
        public static final String COL_NAME_CEPAGE = "cepage";
        public static final String COL_NAME_TAILLE = "taille";
        public static final String COL_NAME_DEBUT_BOIRE = "debboire";
        public static final String COL_NAME_FIN_BOIRE = "finboire";
        public static final String COL_NAME_COMMENTAIRES = "commentaires";
        public static final String COL_NAME_DEGRE = "degre";
        public static final String COL_NAME_DEBUT_TEMP = "debtemp";
        public static final String COL_NAME_FIN_TEMP = "fintemp";
    }
}
