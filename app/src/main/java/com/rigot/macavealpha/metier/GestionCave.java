package com.rigot.macavealpha.metier;

import android.content.Context;

import com.rigot.macavealpha.dao.GestionCaveBdd;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//import com.rigot.macavealpha.parser.CaveParser;

/**
 * Created by Seb on 08/11/13.
 */
public class GestionCave {
    private static GestionCave ourInstance = new GestionCave();

    private GestionCaveBdd bd = null;

    /**
     * Liste des vins
     */
    private List<Vin> liste = new ArrayList<Vin>();

    private GestionCave() {
    }

    /**
     * Lecture du singleton de GestionCave
     *
     * @return l'instance de GestionCave
     */
    public static GestionCave getInstance() {
        return ourInstance;
    }

    public List<Vin> getListeVins() {
        return liste;
    }

    public void AjouterVin(Vin v) {
        // Enregistrer le vin
        bd.AjouterVin(v);
        liste.add(v);
    }

    public void ModifierVin(Vin v) {
        // Enregistrer le vin
        bd.ModifierVin(v);
    }

    /**
     * Charger la liste des vins depuis un flux XML
     *
     * @param in Flux XML
     * @throws java.io.IOException                   Exception sur la lecture du flux
     * @throws org.xmlpull.v1.XmlPullParserException Format XML invalide
     */
    public void ChargerCave(InputStream in) throws IOException, XmlPullParserException {
/*    	CaveParser parser = new CaveParser() ;
        liste = parser.parse(in);*/
    }

    public void ChargerCave(Context context) {
        bd = new GestionCaveBdd(context);
        liste = bd.ChargerCave();
    }

    public Vin getVinPosition(int idVin) {
        Vin retour = null;
        if (idVin >= 0 && idVin < liste.size()) {
            retour = liste.get(idVin);
        }
        return retour;
    }
}
