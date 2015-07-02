package com.rigot.macavealpha.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rigot.macavealpha.dao.CaveDao.VinDao;
import com.rigot.macavealpha.metier.Vin;
import com.rigot.macavealpha.ref.RefAppellation;
import com.rigot.macavealpha.ref.RefCategorie;
import com.rigot.macavealpha.ref.RefCouleur;

import java.util.ArrayList;
import java.util.List;

public class GestionCaveBdd {
    private CaveDbHelper dbHelper = null;

    public GestionCaveBdd(Context context) {
        dbHelper = new CaveDbHelper(context);
    }

    /**
     * Chargement depuis la base de donnees
     *
     * @return Liste des vins de la cave
     */
    public List<Vin> ChargerCave() {
        List<Vin> retour = new ArrayList<Vin>();
        // Lire donn�es en base et charger la liste
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {VinDao._ID,
                VinDao.COL_NAME_NOM,
                VinDao.COL_NAME_ANNEE,
                VinDao.COL_NAME_APPELLATION,
                VinDao.COL_NAME_CATEGORIE,
                VinDao.COL_NAME_COULEUR,
                VinDao.COL_NAME_ESTIMATION,
                VinDao.COL_NAME_NOTE,
                VinDao.COL_NAME_CEPAGE,
                VinDao.COL_NAME_TAILLE,
                VinDao.COL_NAME_DEBUT_BOIRE,
                VinDao.COL_NAME_FIN_BOIRE,
                VinDao.COL_NAME_COMMENTAIRES,
                VinDao.COL_NAME_DEGRE,
                VinDao.COL_NAME_DEBUT_TEMP,
                VinDao.COL_NAME_FIN_TEMP};
        Cursor c = db.query(VinDao.TABLE_NAME, projection, null, null, null,
                null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Vin v = new Vin();
                    v.setId(c.getLong(c.getColumnIndexOrThrow(VinDao._ID)));
                    v.setNom(c.getString(c.getColumnIndexOrThrow(VinDao.COL_NAME_NOM)));
                    v.setAnnee(c.getInt(c.getColumnIndexOrThrow(VinDao.COL_NAME_ANNEE)));
                    String strAppellation = c.getString(c.getColumnIndexOrThrow(VinDao.COL_NAME_APPELLATION));
                    if (strAppellation != null) {
                        v.setAppellation(RefAppellation.valueOf(strAppellation));
                    }
                    String strCategorie = c.getString(c.getColumnIndexOrThrow(VinDao.COL_NAME_CATEGORIE));
                    if (strCategorie != null) {
                        v.setCategorie(RefCategorie.valueOf(strCategorie));
                    }
                    String strCouleur = c.getString(c.getColumnIndexOrThrow(VinDao.COL_NAME_COULEUR));
                    if (strCouleur != null) {
                        v.setCouleur(RefCouleur.valueOf(strCouleur));
                    }

                    v.setEstimation(c.getFloat(c.getColumnIndexOrThrow(VinDao.COL_NAME_ESTIMATION)));
                    v.setNote(c.getFloat(c.getColumnIndexOrThrow(VinDao.COL_NAME_NOTE)));
                    v.setCepage(c.getString(c.getColumnIndexOrThrow(VinDao.COL_NAME_CEPAGE)));
                    v.setTaille(c.getFloat(c.getColumnIndexOrThrow(VinDao.COL_NAME_TAILLE)));
                    v.setDebutBoire(c.getInt(c.getColumnIndexOrThrow(VinDao.COL_NAME_DEBUT_BOIRE)));
                    v.setFinBoire(c.getInt(c.getColumnIndexOrThrow(VinDao.COL_NAME_FIN_BOIRE)));
                    v.setCommentaires(c.getString(c.getColumnIndexOrThrow(VinDao.COL_NAME_COMMENTAIRES)));
                    v.setDegre(c.getFloat(c.getColumnIndexOrThrow(VinDao.COL_NAME_DEGRE)));
                    v.setDebutTemp(c.getInt(c.getColumnIndexOrThrow(VinDao.COL_NAME_DEBUT_TEMP)));
                    v.setFinTemp(c.getInt(c.getColumnIndexOrThrow(VinDao.COL_NAME_FIN_TEMP)));

                    retour.add(v);
                } while (c.moveToNext());
            }
        }
        return retour;
    }

    /**
     * Suppression d'un vin en base
     *
     * @param v vin
     */
    public void SupprimerVin(Vin v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = VinDao._ID + " = ?";
        String selectionArgs[] = {String.valueOf(v.getId())};

        db.delete(VinDao.TABLE_NAME, selection, selectionArgs);
    }

    /**
     * Ajout d'un vin en base
     *
     * @param v le vin a ajouter (l'id sera mis a jour)
     */
    public void AjouterVin(Vin v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Insert en base
        long result = db.insert(VinDao.TABLE_NAME, null, lireValeurs(v));

        // Ajout de l'id au vin
        v.setId(result);
    }

    /**
     * Modifie un vin en base
     *
     * @param v Le vin a modifier
     */
    public void ModifierVin(Vin v) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = VinDao._ID + " = ?";
        String selectionArgs[] = {String.valueOf(v.getId())};

        db.update(VinDao.TABLE_NAME, lireValeurs(v), selection, selectionArgs);
    }

    /**
     * Lire les valeurs d'un vin dans un ContenValues
     *
     * @param v le vin
     * @return ContentValue de l'objet {@link com.rigot.macavealpha.dao.CaveDao.VinDao}
     */
    private ContentValues lireValeurs(Vin v) {
        ContentValues values = new ContentValues();
        values.put(VinDao.COL_NAME_NOM, v.getNom());
        values.put(VinDao.COL_NAME_ANNEE, v.getAnnee());
        String strAppellation = null;
        if (v.getAppellation() != null) {
            strAppellation = v.getAppellation().name();
        }
        values.put(VinDao.COL_NAME_APPELLATION, strAppellation);

        String strCategorie = null;
        if (v.getCategorie() != null) {
            strCategorie = v.getCategorie().name();
        }
        values.put(VinDao.COL_NAME_CATEGORIE, strCategorie);

        String strCouleur = null;
        if (v.getCouleur() != null) {
            strCouleur = v.getCouleur().name();
        }
        values.put(VinDao.COL_NAME_COULEUR, strCouleur);
        values.put(VinDao.COL_NAME_ESTIMATION, v.getEstimation());
        values.put(VinDao.COL_NAME_NOTE, v.getNote());
        values.put(VinDao.COL_NAME_CEPAGE, v.getCepage());
        values.put(VinDao.COL_NAME_TAILLE, v.getTaille());
        values.put(VinDao.COL_NAME_DEBUT_BOIRE, v.getDebutBoire());
        values.put(VinDao.COL_NAME_FIN_BOIRE, v.getFinBoire());
        values.put(VinDao.COL_NAME_COMMENTAIRES, v.getCommentaires());
        values.put(VinDao.COL_NAME_DEGRE, v.getDegre());
        values.put(VinDao.COL_NAME_DEBUT_TEMP, v.getDebutTemp());
        values.put(VinDao.COL_NAME_FIN_TEMP, v.getFinTemp());

        return values;
    }
}
