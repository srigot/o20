package com.rigot.macavealpha.metier;

import com.rigot.macavealpha.ws.VinEndpointCall;

import java.io.IOException;
import java.util.List;

import fr.rigot.cavavin.backend.vins.model.Vin;

/**
 * Auteur : Seb
 * Date de creation : 08/11/13.
 */
public class GestionCave {
    private static GestionCave ourInstance = new GestionCave();

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

    public void AjouterVin(Vin v) throws IOException {
        new VinEndpointCall().addVin(v);
        getListeVins().add(v);
    }

    public void ModifierVin(Vin v) throws IOException {
        new VinEndpointCall().modifierVin(v);
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
