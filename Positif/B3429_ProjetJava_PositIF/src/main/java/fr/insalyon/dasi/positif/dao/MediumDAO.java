/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.Medium;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author arasoldier
 */
public class MediumDAO {
    
    public static List<Medium> obtenirTous() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("SELECT m FROM Medium m").getResultList();
    }
    
    public static void modifier(Medium medium) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(medium);
    }
}
