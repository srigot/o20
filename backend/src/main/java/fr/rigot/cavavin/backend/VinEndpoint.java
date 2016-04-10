package fr.rigot.cavavin.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.BadRequestException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;

import fr.rigot.cavavin.backend.metier.Emplacement;
import fr.rigot.cavavin.backend.metier.Vin;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by a145426 on 03/02/2016.
 */
@Api(
        name = "vins",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.cavavin.rigot.fr",
                ownerName = "backend.cavavin.rigot.fr",
                packagePath = ""
        )
)
public class VinEndpoint {

    static {
        ObjectifyService.register(Vin.class);
        ObjectifyService.register(Emplacement.class);
    }

    /**
     * Cette API permet de recuperer tous les vins de la base de données
     *
     * @return la liste des vins
     */
    @ApiMethod(name = "getAll",
            path = "vin",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<Vin> recupererListeVins() {
        final List<Vin> listeRetour = new ArrayList<Vin>();

        // Cette methode retourne la liste des vins presents en base
        final Query<Vin> vins = ofy().load().type(Vin.class).chunkAll();
        for (Vin v : vins) {
            listeRetour.add(v);
        }
        return listeRetour;
    }

    /**
     * Cette méthode permet de modifier un vin déjà enregistrer dans la base de données
     *
     * @param v Le vin à modifier
     */
    @ApiMethod(name = "modifyVin",
            path = "vin",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public void modifierVin(Vin v) {
        ofy().save().entity(v).now();
    }

    /**
     * Cette méthode permet d'ajouter un nouveau vin dans la base de données
     *
     * @param v Le vin à ajouter
     */
    @ApiMethod(name = "addVin",
            path = "vin",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void creerVin(Vin v) {
        ofy().save().entity(v).now();
    }

    /**
     * Cette méthode permet de supprimer un vin de la base de données
     *
     * @param v Le vin à supprimer
     * @throws BadRequestException Exception levée si le vin n'a pas pu etre supprimé
     */
    @ApiMethod(name = "supprimerVin",
            path = "vin",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void supprimerVin(Vin v) throws BadRequestException {
        if (null == v.getId()) {
            throw new BadRequestException("");
        }
        ofy().delete().entity(v).now();
    }

    /**
     * Cette API permet de modifier un emplacement en base de données
     *
     * @param emplacement Emplacement à modifier
     */
    @ApiMethod(name = "modifierEmplacement",
            path = "emplacement",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void modifierEmplacement(Emplacement emplacement) {
        ofy().save().entity(emplacement).now();
    }

    @ApiMethod(name = "getEmplacement",
            path = "emplacement",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<Emplacement> getRangement() {
        final List<Emplacement> retour = new ArrayList<Emplacement>();
        for (Emplacement e : ofy().load().type(Emplacement.class).chunkAll()) {
            retour.add(e);
        }
        return retour;
    }


}
