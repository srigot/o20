package com.rigot.macavealpha.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Auteur : Seb
 * Date de creation : 05/11/2015
 */
public class Cave implements Serializable {

    /**
     * Serial ID Cave
     */
    private static final long serialVersionUID = 2030111255068699493L;

    private List<Vin> listeVin = new ArrayList<Vin>();

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Vin> getListeVin() {
        return listeVin;
    }

    public void setListeVin(List<Vin> listeVin) {
        this.listeVin = listeVin;
    }

    public void addVin(Vin v) {
        this.listeVin.add(v);
    }
}
