package com.rigot.macavealpha.ws;

import android.util.Log;

import com.rigot.macavealpha.metier.Cave;
import com.rigot.macavealpha.metier.Vin;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Auteur : Seb
 * Date de creation : 27/09/2015
 */
public class VinController {
    public final ClientResource cr = new ClientResource(
            EngineConfiguration.gae_path + "/rest/vin");

    public VinController() {
        EngineConfiguration.getInstance();
    }

    public void createVin(Vin vin) throws WebServiceException {
        final VinControllerInterface uci = cr.wrap(VinControllerInterface.class);

        try {
            JacksonRepresentation<Vin> vinJacksonRepresentation = new JacksonRepresentation<Vin>(vin);
            OutputStream os = new OutputStream() {
                private StringBuilder string = new StringBuilder();

                @Override
                public void write(int oneByte) throws IOException {
                    this.string.append((char) oneByte);
                }

                public String toString() {
                    return this.string.toString();
                }
            };
            vinJacksonRepresentation.write(os);
            ResultWS rs = uci.createVin(os.toString());
            if (rs.estErreur()) {
                Log.i("VinController", rs.getLibelleErreur());
                throw new WebServiceException(rs.getLibelleErreur());
            }
            Log.i("VinController", "Creation OK");
        } catch (Exception e) {
            Log.i("VinController", "Creation failed !");
            throw new WebServiceException("Création impossible. Vérifier votre connexion");
        }
    }

    public void updateCave(Cave cave) throws WebServiceException {
        final VinControllerInterface uci = cr.wrap(VinControllerInterface.class);

        try {
//            uci.updateCave(cave);
            Log.i("VinController", "Creation OK");
        } catch (Exception e) {
            Log.i("VinController", "Creation failed !");
            throw new WebServiceException("Impossible d'ajouter la cave.");
        }
    }

    public Cave getAllVins() {
        final VinControllerInterface uci = cr.wrap(VinControllerInterface.class);
        return uci.getAllVins();
    }

    public Vin getVinById() {
        final VinControllerInterface uci = cr.wrap(VinControllerInterface.class);
//        return uci.getVinByID(Long.getLong("1"));
        return null;
    }
}
