package com.rigot.macavealpha.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rigot.macavealpha.dao.CaveDao.VinDao;

public class CaveDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "Cave.db";

    private static final String SQL_CREATE_CAVEDB = "CREATE TABLE " + VinDao.TABLE_NAME + " (" +
            VinDao._ID + " INTEGER PRIMARY KEY, " +
            VinDao.COL_NAME_NOM + " TEXT, " +
            VinDao.COL_NAME_ANNEE + " INTEGER, " +
            VinDao.COL_NAME_APPELLATION + " TEXT, " +
            VinDao.COL_NAME_CATEGORIE + " TEXT, " +
            VinDao.COL_NAME_COULEUR + " TEXT, " +
            VinDao.COL_NAME_ESTIMATION + " REAL, " +
            VinDao.COL_NAME_NOTE + " REAL, " +
            VinDao.COL_NAME_CEPAGE + " TEXT, " +
            VinDao.COL_NAME_TAILLE + " REAL, " +
            VinDao.COL_NAME_DEBUT_BOIRE + " INTEGER, " +
            VinDao.COL_NAME_FIN_BOIRE + " INTEGER, " +
            VinDao.COL_NAME_COMMENTAIRES + " TEXT, " +
            VinDao.COL_NAME_DEGRE + " REAL, " +
            VinDao.COL_NAME_DEBUT_TEMP + " INTEGER, " +
            VinDao.COL_NAME_FIN_TEMP + " INTEGER)";

    private static final String SQL_DELETE_CAVEDB = "DROP TABLE IF EXISTS " + VinDao.TABLE_NAME;

    public CaveDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CAVEDB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CAVEDB);
        onCreate(db);

    }

}
