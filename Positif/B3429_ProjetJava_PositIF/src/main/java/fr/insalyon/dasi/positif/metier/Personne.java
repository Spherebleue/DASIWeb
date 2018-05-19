/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Personne réelle (Employé ou client) pouvant se connecter au service
 * @author fmutin
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_PERSONNE")
@DiscriminatorValue("MERE")
public abstract class Personne implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long ID;

    /**
     * nom de famille de la Personne
     */
    protected String nom;

    /**
     * prénom de la Personne
     */
    protected String prenom;

    /**
     * mot de passe en clair de la Personne
     */
    protected String motDePasse;
    
    /**
     * Constructeur par défaut pour JPA
     */
    public Personne() {}
    
    /**
     * 
     * @param nom nom de famille de la Personne
     * @param prenom prénom de la Personne
     * @param motDePasse mot de passe en clair de la Personne
     */
    public Personne(String nom, String prenom, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "ID=" + ID + ", nom=" + nom + ", prenom=" + prenom + ", motDePasse=" + motDePasse;
    }

    /**
     * 
     * @return nom de famille de la Personne
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return prénom de la Personne
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @return mot de passe en clair de la Personne
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     *
     * @return identifiant unique de la Personne
     */
    public Long getID() {
        return ID;
    }

    /**
     *
     * @param ID identifiant unique de la Personne
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     *
     * @param nom nom de famille de la Personne
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @param prenom prénom de la Personne
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @param motDePasse mot de passe en clair de la Personne
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
}
