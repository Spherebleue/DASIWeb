/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Medium (joué par un Employé) avec qui un Client peut demander une consultation
 * @author arasoldier
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_MEDIUM")
@DiscriminatorValue("MERE")
public abstract class Medium implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String nom;
    private String bio;
    @ManyToMany
    private List<Employe> employes;

    /**
     *
     */
    @OneToMany(mappedBy="medium")
    protected List<Conversation> conversations;

    /**
     *
     */
    public Medium() {
    }
    
    /**
     *
     * @param nom nom du Medium
     * @param bio biographie du Medium
     */
    public Medium(String nom, String bio) {
        this.nom = nom;
        this.bio = bio;
        this.conversations = new ArrayList<>();
        this.employes = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "ID=" + ID + ", nom=" + nom + ", bio=" + bio;
    }

    /**
     *
     * @return Conversations auxquelles a participé le Medium
     */
    public List<Conversation> getConversations() {
        return conversations;
    }

    /**
     *
     * @param conversations Conversations auxquelles a participé le Medium
     */
    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }
    
    /**
     *
     * @return Employés qui peuvent jouer le Medium
     */
    public List<Employe> getEmployes() {
        return employes;
    }

    /**
     *
     * @param employes Employés qui peuvent jouer le Medium
     */
    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    /**
     *
     * @return nom du Medium
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom nom du Medium
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return biographie du Medium
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio biographie du Medium
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return identifiant unique du Medium
     */
    public Long getID() {
        return ID;
    }

    /**
     *
     * @param ID identifiant unique du Medium
     */
    public void setID(Long ID) {
        this.ID = ID;
    }
    
}
