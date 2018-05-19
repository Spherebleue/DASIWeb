/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.Astrologue;
import fr.insalyon.dasi.positif.metier.Employe;
import fr.insalyon.dasi.positif.metier.Tarologue;
import fr.insalyon.dasi.positif.metier.Voyant;
import java.util.Arrays;
import javax.persistence.EntityManager;

/**
 *
 * @author flori
 */
public class Initialisation {
    
    public static void Initialiser() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        //Médiums (1/2)
        Voyant gwenael = new Voyant("Boule de Cristal","Gwenaël","Spécialiste des grandes conversations au-delà de TOUTES les frontières.");
        gwenael.setID(1l);
        Voyant dalmarre = new Voyant("Marc de Café","J. Dalmarre","Votre avenir est devant vous: regardons le ensemble!");
        dalmarre.setID(2l);
        Tarologue irma = new Tarologue("Tarot de Marseille","Mme Irma","Comprenez votre entourage grâce à mes cartes! Résultats rapides.");
        irma.setID(3l);
        Tarologue nguyinia = new Tarologue("Tarot de Brocéliande","Mme Lisa Maria NGUYINIA","Mes cartes spécialisées pour la région Bretagne répondront à toutes vos questions personnelles.");
        nguyinia.setID(4l);
        Astrologue sara = new Astrologue("Ecole Normale Supérieure d'Astrologie (ENS-Astro)",2006,"Mme Sara","Basée à Champigny-sur-Marne, Mme Sara vous révèlera votre avenir pour éclairer votre passé.");
        sara.setID(5l);
        Astrologue mounia = new Astrologue("Institut des Nouveaux Savoirs Astrologiques",2010,"Mme Mounia Mounia","Avenir, avenir, que nous réserves-tu? N'attendez plus, demandez à me consulter!");
        mounia.setID(6l);
        
        //Employés
        Employe zouhair = new Employe("GIREUX","Zouhair","GXzr","0606006060",true,Arrays.asList(gwenael,dalmarre,irma));
        zouhair.setID(586l);
        Employe nicolas = new Employe("TCHIUMAKOVA","Nicolas","TAns","0628448260",true,Arrays.asList(irma,nguyinia,sara,mounia));
        nicolas.setID(6703l);
        Employe cedric = new Employe("KEMARO","Cédric","KOcc","0617283940",true,Arrays.asList(gwenael,sara));
        cedric.setID(3745l);
        Employe olena = new Employe("PAMITESCU","Olena","PUoa","0678912345",true,Arrays.asList(gwenael,dalmarre,irma,nguyinia,sara,mounia));
        olena.setID(29l);
        
        //Médiums (2/2)
        gwenael.setEmployes(Arrays.asList(zouhair, cedric, olena));
        dalmarre.setEmployes(Arrays.asList(zouhair, olena));
        irma.setEmployes(Arrays.asList(zouhair, nicolas, olena));
        nguyinia.setEmployes(Arrays.asList(nicolas, olena));
        sara.setEmployes(Arrays.asList(nicolas, cedric, olena));
        mounia.setEmployes(Arrays.asList(nicolas, olena));
        
        //Persistance
        em.persist(gwenael);
        em.persist(dalmarre);
        em.persist(irma);
        em.persist(nguyinia);   
        em.persist(sara);    
        em.persist(mounia);    
        em.persist(zouhair);      
        em.persist(nicolas);      
        em.persist(cedric);      
        em.persist(olena);  
    }
}
