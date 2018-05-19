/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * Conversation entre un Client et un Medium (joué par un Employé)
 * @author arasoldier
 */
@Entity
public class Conversation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heureDebut;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heureFin;
    private String commentaire;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Client client;
    
    /**
     *
     */
    public Conversation() {
    }

    /**
     *
     * @param employe Employé participant à la conversation
     * @param medium Medium représenté par l'Employé participant à la conversation
     * @param client Client participan à la conversation
     */
    public Conversation(Employe employe, Medium medium, Client client) {
        this.employe = employe;
        this.medium = medium;
        this.client = client;
        client.getConversations().add(this);
        employe.getConversations().add(this);
        medium.getConversations().add(this);
    }

    @Override
    public String toString() {
        return "Conversation{" + "ID=" + ID + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", commentaire=" + commentaire + ", employe=" + employe + ", medium=" + medium + ", client=" + client + '}';
    }
    
    /**
     *
     * @return heure de début de la conversation
     */
    public Date getHeureDebut() {
        return heureDebut;
    }

    /**
     *
     * @param heureDebut heure de début de la conversation
     */
    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     *
     * @return heure de fin de la conversation
     */
    public Date getHeureFin() {
        return heureFin;
    }

    /**
     *
     * @param heureFin heure de fin de la conversation
     */
    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    /**
     *
     * @return commentaire à propos de la conversation
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     *
     * @param commentaire commentaire à propos de la conversation
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     *
     * @return identifiant unique de la conversation
     */
    public Long getID() {
        return ID;
    }

    /**
     *
     * @param ID identifiant unique de la conversation
     */
    public void setID(Long ID) {
        this.ID = ID;
    }
    
    /**
     *
     * @return Employé participant à la conversation
     */
    public Employe getEmploye() {
        return employe;
    }

    /**
     *
     * @param employe Employé participant à la conversation
     */
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    /**
     *
     * @return Medium représenté par l'Employé participant à la conversation
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     *
     * @param medium Medium représenté par l'Employé participant à la conversation
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     *
     * @return Client participant à la conversation
     */
    public Client getClient() {
        return client;
    }

    /**
     *
     * @param client Client participant à la conversation
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
