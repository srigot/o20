package com.rigot.macavealpha.metier;

import com.rigot.macavealpha.ws.VinController;
import com.rigot.macavealpha.ws.WebServiceException;

import java.util.List;

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
    private Cave cave = null;

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
        return (cave == null) ? null : cave.getListeVin();
    }

    public void AjouterVin(Vin v) throws WebServiceException {
        // Enregistrer le vin
        VinController vinController = new VinController();
        vinController.createVin(v);
        cave.addVin(v);
        vinController.updateCave(cave);
    }

    public void ModifierVin(Vin v) {
        // TODO Enregistrer le vin
        // bd.ModifierVin(v);
    }

    public void ChargerCave() {
//        VinController vinController = new VinController();
        VinController vinController = new VinController();
        cave = vinController.getAllVins();
    }

    public Vin getVinPosition(int idVin) {
        Vin retour = null;
        if (cave != null) {
            if (idVin >= 0 && idVin < cave.getListeVin().size()) {
                retour = cave.getListeVin().get(idVin);
            }
        }
        return retour;
    }
}
