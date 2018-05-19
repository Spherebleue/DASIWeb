/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import javax.persistence.Entity;

/**
 * Medium de type Tarologue caractérisé par un jeu de cartes
 * @author aina
 */
@Entity
public class Tarologue extends Medium {

    /**
     *
     * @param cartes nom du jeu de cartes du Tarologue
     */
    public void setCartes(String cartes) {
        this.cartes = cartes;
    }

    private String cartes;
    
    /**
     * Constructeur par défaut pour JPA
     */
    public Tarologue() {
    }
    
    /**
     *
     * @param cartes nom du jeu de cartes du Tarologue
     * @param nom nom du Tarologue
     * @param bio biographie du Tarologue
     */
    public Tarologue(String cartes, String nom, String bio) {
        super(nom, bio);
        this.cartes = cartes;
    }
    
    @Override
    public String toString() {
        return "Tarologue{" + super.toString() + ", cartes=" + cartes + '}';
    }

    /**
     *
     * @return nom du jeu de cartes du Tarologue
     */
    public String getCartes() {
        return cartes;
    }
    
}
