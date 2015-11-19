package com.rigot.macavealpha.ws;

import java.io.Serializable;

/**
 * Auteur : Seb
 * Date de creation : 10/11/2015
 */
public class ResultWS implements Serializable {

    public static final Integer RETOUR_OK = 0;
    public static final Integer RETOUR_KO = 1;
    public static final Integer RETOUR_WARNING = 2;
    /**
     *
     */
    private static final long serialVersionUID = 4493629790613686902L;
    private Integer codeErreur;

    private String libelleErreur;

    public ResultWS(Integer codeErreur, String libelleErreur) {
        this.codeErreur = codeErreur;
        this.libelleErreur = libelleErreur;
    }

    public ResultWS() {

    }

    public ResultWS(Integer codeErreur) {
        this.codeErreur = codeErreur;
    }

    public Integer getCodeErreur() {
        return codeErreur;
    }

    public void setCodeErreur(Integer codeErreur) {
        this.codeErreur = codeErreur;
    }

    public String getLibelleErreur() {
        return libelleErreur;
    }

    public void setLibelleErreur(String libelleErreur) {
        this.libelleErreur = libelleErreur;
    }

    public boolean estErreur() {
        return !(RETOUR_OK.equals(codeErreur));
    }
}
