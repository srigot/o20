package com.rigot.macavealpha.ws;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.rigot.cavavin.backend.vins.Vins;
import fr.rigot.cavavin.backend.vins.model.Vin;
import fr.rigot.cavavin.backend.vins.model.VinCollection;

/**
 * Auteur : a145426
 * Date de creation : 10/04/2016.
 */
public class VinEndpointCall {
    private static Vins vinsService = null;

    /**
     * Initialisation de la connexion au EndPoint
     */
    private void initCall() {
        if (vinsService == null) {
            /* Debug Local
            Vins.Builder builder = new Vins.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            */
            Vins.Builder builder = new Vins.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://apicaveavin.appspot.com/_ah/api");
            vinsService = builder.build();
        }
    }

    /**
     * Recuperation de la liste des vins
     *
     * @return la liste des vins
     * @throws IOException Exception si la connexion echoue
     */
    public List<Vin> getAll() throws IOException {
        initCall();
        List<Vin> retour;

        final VinCollection liste = vinsService.getAll().execute();
        if (liste != null) {
            retour = liste.getItems();
        } else {
            retour = new ArrayList<>();
        }
        return retour;
    }

    /**
     * Ajout d'un vin en base
     *
     * @param v le vin a ajouter
     * @throws IOException Exception en cas d'erreur de connexion
     */
    public void addVin(Vin v) throws IOException {
        vinsService.addVin(v).execute();
    }

    /**
     * Modification d'un vin en base
     *
     * @param v le vin a modifier
     * @throws IOException Exception en cas d'erreur de connexion
     */
    public void modifierVin(Vin v) throws IOException {
        vinsService.modifyVin(v).execute();
    }
}
