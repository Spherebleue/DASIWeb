/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.metier;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Employé du service Posit'IF
 * @author fmutin
 */
@Entity
public class Employe extends Personne {

    /**
     * numéro de téléphone de l'Employé
     */
    protected String telephone;

    /**
     * disponibilité de l'Employé (vrai : disponible, faux : indisponible)
     */
    protected boolean disponible;

    /**
     * Mediums que peut jouer l'Employé
     */
    @ManyToMany(mappedBy="employes")
    protected List<Medium> mediums;

    /**
     * Conversations auxquelles a participé l'Employé
     */
    @OneToMany(mappedBy="employe")
    protected List<Conversation> conversations;

    /**
     * Constructeur par défaut pour JPA
     */
    public Employe() {}
    
    /**
     *
     * @param nom nom de famille de l'Employé
     * @param prenom prénom de l'Employé
     * @param motDePasse mot de passe en clair de l'Employé
     * @param telephone numéro de téléphone de l'Employé
     * @param disponible disponibilité de l'employé
     */
    public Employe(String nom, String prenom, String motDePasse, String telephone, boolean disponible) {
        super(nom, prenom, motDePasse);
        this.telephone = telephone;
        this.disponible = disponible;
        this.conversations = new ArrayList<>();
        
    }
    
    /**
     *
     * @param nom nom de famille de l'Employé
     * @param prenom prénom de l'Employé
     * @param motDePasse mot de passe en clair de l'Employé
     * @param telephone numéro de téléphone de l'Employé
     * @param disponible disponibilité de l'employé
     * @param mediums liste des Mediums que peut jouer l'Employé
     */
    public Employe(String nom, String prenom, String motDePasse, String telephone, boolean disponible, List<Medium> mediums) {
        super(nom, prenom, motDePasse);
        this.telephone = telephone;
        this.disponible = disponible;
        this.conversations = new ArrayList<>();
        
        this.mediums = mediums;
        
        for(Medium m : mediums) {
            m.getEmployes().add(this);
        }
        
    }
    

    @Override
    public String toString() {
        return "Employe{" + super.toString() + ", telephone=" + telephone + ", disponible=" + disponible + ", mediums=" + mediums + '}';
    }
    
    /**
     *
     * @return Conversations auxquelles a participé l'Employé
     */
    public List<Conversation> getConversations() {
        return conversations;
    }

    /**
     *
     * @param conversations Conversations auxquelles a participé l'Employé
     */
    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    /**
     *
     * @return Mediums que peut jouer l'Employé
     */
    public List<Medium> getMediums() {
        return mediums;
    }

    /**
     *
     * @param mediums Mediums que peut jouer l'Employé
     */
    public void setMediums(List<Medium> mediums) {
        this.mediums = mediums;
    }

    /**
     *
     * @return numéro de téléphone de l'Employé
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone numéro de téléphone de l'Employé
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return vrai si l'Employé est disponible, faux sinon
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     *
     * @param disponible vrai si l'Employé est disponible, faux sinon
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
}
