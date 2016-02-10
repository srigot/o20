package fr.rigot.cavavin.backend.ref;

/**
 * Created by Seb on 11/11/13.
 */
public enum RefAppellation {
    BORDEAUX("Bordeaux"), BOURGOGNE("Bourgogne");

    private final String nom;

    RefAppellation(final String pNom) {
        nom = pNom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
