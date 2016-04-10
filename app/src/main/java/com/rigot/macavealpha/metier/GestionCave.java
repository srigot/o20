package com.rigot.macavealpha.metier;

import com.rigot.macavealpha.ws.VinEndpointCall;
import com.rigot.macavealpha.ws.WebServiceException;

import java.io.IOException;
import java.util.List;

import fr.rigot.cavavin.backend.vins.model.Vin;

//import com.rigot.macavealpha.parser.CaveParser;

/**
 * Created by Seb on 08/11/13.
 */
public class GestionCave {
    private static GestionCave ourInstance = new GestionCave();

//    private GestionCaveBdd bd = null;

    /**
     * Liste des vins
     */
    private List<Vin> listeVins = null;

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
        return listeVins;
    }

    public void AjouterVin(Vin v) throws WebServiceException {
        // TODO Enregistrer le vin
    }

    public void ModifierVin(Vin v) {
        // TODO Enregistrer le vin
        // bd.ModifierVin(v);
    }

    public void ChargerCave() throws IOException {
        listeVins = new VinEndpointCall().getAll();
    }

    public Vin getVinPosition(int idVin) {
        Vin retour = null;
        if (listeVins != null) {
            if (idVin >= 0 && idVin < getListeVins().size()) {
                retour = getListeVins().get(idVin);
            }
        }
        return retour;
    }
}
