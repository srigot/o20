package fr.rigot.cavavin.backend;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.response.BadRequestException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.util.Closeable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import fr.rigot.cavavin.backend.metier.Vin;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by a145426 on 08/02/2016.
 */
public class VinEndpointTest {
    public static final String TEST_NOM = "TEST Nom";
    private static final String TEST_NOM_MODIFIE = "Test nom modifie";
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private Closeable objectifyService;

    @Before
    public void setUp() {
        helper.setUp();
        objectifyService = ObjectifyService.begin();
    }

    @After
    public void tearDown() {
        helper.tearDown();
        objectifyService.close();
    }

    @Test
    public void testRecupererListeVins() {
        // Recuperation d'une liste vide
        List<Vin> liste = new VinEndpoint().recupererListeVins();
        assertNotNull("Liste vide non nulle", liste);
        assertEquals("Liste vide", 0, liste.size());

        // Ajouter un vin dans la BDD
        Vin v = new Vin();
        v.setNom(TEST_NOM);
        ofy().save().entity(v).now();

        liste = new VinEndpoint().recupererListeVins();
        assertNotNull("Liste renseignee", liste);
        assertEquals("Liste contient 1 element", 1, liste.size());
        assertEquals("Nom de l'element", TEST_NOM, liste.get(0).getNom());
    }

    @Test
    public void testCreerVin() {
        Vin v = new Vin();
        v.setNom(TEST_NOM);
        new VinEndpoint().creerVin(v);

        Vin result = ofy().load().type(Vin.class).first().now();
        assertNotNull("Vin existant", result);
        assertEquals("Nom de l'element", TEST_NOM, result.getNom());
    }

    @Test
    public void testModifierVin() {
        // Creer un vin
        Vin v = new Vin();
        v.setNom(TEST_NOM);
        ofy().save().entity(v).now();

        // le modifier
        v.setNom(TEST_NOM_MODIFIE);
        new VinEndpoint().modifierVin(v);

        // Verifier qu'en base la valeur est correcte
        Vin result = ofy().load().type(Vin.class).first().now();
        assertNotNull("Vin existant", result);
        assertEquals("Nom de l'element", TEST_NOM_MODIFIE, result.getNom());
    }

    @Test
    public void testSupprimerVin() throws ServiceException {
        // Creer le vin
        Vin v = new Vin();
        v.setNom(TEST_NOM);
        ofy().save().entity(v).now();

        Query<Vin> liste = ofy().load().type(Vin.class).chunkAll();

        assertEquals("Nombre de vins avant", 1, liste.count());
        // Appel du service de suppression
        new VinEndpoint().supprimerVin(v);

        // Verifier qu'il n'existe plus de vin en base
        assertEquals("Nombre de vins apres", 0, liste.count());
    }

    @Test
    public void testSupprimerVinInexistant() throws ServiceException {
        // 2. Test du cas ou le vin n'existe pas
        Vin v = new Vin();
        v.setId(Long.parseLong("123"));
        new VinEndpoint().supprimerVin(v);
        // --> Pas de plantage, retourne OK
    }

    @Test(expected = BadRequestException.class)
    public void testSupprimerVinSansID() throws ServiceException {
        // 3. Test du cas ou le vin ne possede pas d'ID
        new VinEndpoint().supprimerVin(new Vin());
    }

    @Test
    public void testGetRangement() {
        // TODO Tester le GetRangement
        // fail("A IMPLEMENTER");
    }
}
