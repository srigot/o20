package fr.rigot.cavavin.backend.ref;

/**
 * Created by Seb on 11/11/13.
 */
public enum RefCategorie {
    VINS("Vins"),
    SPIRITUEUX("Spiritueux"),
    CHAMPAGNES("Champagnes"),
    BIERES("Bieres");

    /**
     * Libelle de la categorie
     */
    private String libelle;

    RefCategorie(final String pLibelle) {
        libelle = pLibelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
