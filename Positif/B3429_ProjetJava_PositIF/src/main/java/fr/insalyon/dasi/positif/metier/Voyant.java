/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import javax.persistence.Entity;

/**
 * Medium de type Voyant caractérisé par un support.
 * @author aina
 */
@Entity
public class Voyant extends Medium {

    private String support;

    /**
     * Constructeur par défaut pour JPA
     */
    public Voyant() {
    }

    /**
     *
     * @param support nom du support qu'utilise le Voyant
     * @param nom nom du Voyant
     * @param bio biographie du Voyant
     */
    public Voyant(String support, String nom, String bio) {
        super(nom, bio);
        this.support = support;
    }
    
    @Override
    public String toString() {
        return "Voyant{" + super.toString() + ", support=" + support + '}';
    }

    /**
     *
     * @return nom du support qu'utilise le Voyant
     */
    public String getSupport() {
        return support;
    }

    /**
     *
     * @param support nom du support qu'utilise le Voyant
     */
    public void setSupport(String support) {
        this.support = support;
    }

}
