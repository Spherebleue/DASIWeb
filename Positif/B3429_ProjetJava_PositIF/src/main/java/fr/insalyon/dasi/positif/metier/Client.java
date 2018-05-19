/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Client du service Posit'IF
 * @author fmutin
 */
@Entity
public class Client extends Personne implements Serializable {

    /**
     *
     */
    protected char civilite;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Column(unique=true)
    private String adresse;
    private String signeZodiaque;
    private String signeChinois;
    private String animalTotem;
    private String couleur;

    /**
     *
     */
    @OneToMany(mappedBy="client")
    protected List<Conversation> conversations;

    /**
     *
     */
    public Client() {}

    /**
     *
     * @param nom Le nom de famille du client
     * @param prenom Le prénom du client
     * @param motDePasse Le mot de passe en clair du client
     * @param civilite La civilité du client (M ou F)
     * @param dateNaissance La date de naissance du client
     * @param adresse L'adresse e-mail du client
     */
    public Client(String nom, String prenom, String motDePasse, char civilite, Date dateNaissance, String adresse) {
        super(nom, prenom, motDePasse);
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.conversations = new ArrayList<>();
    }
    
    /**
     * Crée un client complet pour tests.
     * @param nom Le nom de famille du client
     * @param prenom Le prénom du client
     * @param motDePasse Le mot de passe en clair du client
     * @param civilite La civilité du client (M ou F)
     * @param dateNaissance La date de naissance du client
     * @param adresse L'adresse e-mail du client
     * @param signeZodiaque Le signe du zodiaque du client
     * @param signeChinois Le signe chinois du client
     * @param animalTotem L'animal totem du client
     * @param couleur La couleur "préférée" du client
     */
    public Client(String nom, String prenom, String motDePasse, char civilite, Date dateNaissance, String adresse, String signeZodiaque, String signeChinois, String animalTotem, String couleur) {
        super(nom, prenom, motDePasse);
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.animalTotem = animalTotem;
        this.couleur = couleur;
        this.conversations = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() + ", civilite=" + civilite + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", signeZodiaque=" + signeZodiaque + ", signeChinois=" + signeChinois + ", animalTotem=" + animalTotem + ", couleur=" + couleur + '}';
    }
    
    /**
     *
     * @return liste des conversations auxquelles a participé le client
     */
    public List<Conversation> getConversations() {
        return conversations;
    }

    /**
     *
     * @param conversations liste des conversations auxquelles a participé le client
     */
    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }
    
    /**
     *
     * @return civilité du client (M ou F)
     */
    public char getCivilite() {
        return civilite;
    }

    /**
     *
     * @param civilite civilité du client (M ou F)
     */
    public void setCivilite(char civilite) {
        this.civilite = civilite;
    }

    /**
     *
     * @return date de naissance du client
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     *
     * @param dateNaissance date de naissance du client
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     *
     * @return adresse e-mail du client
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse adresse e-mail du client
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @return signe du Zodiaque du client
     */
    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    /**
     *
     * @param signeZodiaque signe du Zodiaque du client
     */
    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    /**
     *
     * @return signe du chinois du client
     */
    public String getSigneChinois() {
        return signeChinois;
    }

    /**
     *
     * @param signeChinois signe du chinois du client
     */
    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    /**
     *
     * @return animal totem du client
     */
    public String getAnimalTotem() {
        return animalTotem;
    }

    /**
     *
     * @param animalTotem animal totem du client
     */
    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    /**
     *
     * @return couleur "préférée" du client
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     *
     * @param couleur couleur "préférée" du client
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    
}
