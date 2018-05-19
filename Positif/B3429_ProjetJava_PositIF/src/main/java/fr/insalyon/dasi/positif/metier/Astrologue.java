/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import javax.persistence.Entity;

/**
 * Medium de type Astrologue caractérisé par des études (école et année de promotion)
 * @author aina
 */
@Entity
public class Astrologue extends Medium {

    @Override
    public String toString() {
        return "Astrologue{"+ super.toString() + ", ecole=" + ecole + ", promotion=" + promotion + '}';
    }

    /**
     * @return ecole L'école où à étudié l'Astrologue
     */
    public String getEcole() {
        return ecole;
    }

    /**
     *
     * @param ecole L'école où à étudié l'Astrologue
     */
    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    /**
     *
     * @return L'année de fin des études de l'Astrologue
     */
    public int getPromotion() {
        return promotion;
    }

    /**
     *
     * @param promotion L'année de fin des études de l'Astrologue
     */
    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    private String ecole;
    private int promotion;

    /**
     * Constructeur par défaut pour JPA
     */
    public Astrologue() {
    }

    /**
     * Crée un Astrologue à partir de tous ses attrubuts.
     * @param ecole L'école où à étudié l'Astrologue
     * @param promotion L'année de fin des études de l'Astrologue
     * @param nom Le nom de l'Astrologue
     * @param bio La biographie de l'Astrologue
     */
    public Astrologue(String ecole, int promotion, String nom, String bio) {
        super(nom, bio);
        this.ecole = ecole;
        this.promotion = promotion;
    }
}
