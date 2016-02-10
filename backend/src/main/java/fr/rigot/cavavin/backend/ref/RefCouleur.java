package fr.rigot.cavavin.backend.ref;


/**
 * Created by Seb on 11/11/13.
 */
public enum RefCouleur {
    ROUGE("Rouge"),
    BLANC("Blanc"),
    ROSE("Rose");

    private String libelle;

    RefCouleur(final String pLibelle) {
        libelle = pLibelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
