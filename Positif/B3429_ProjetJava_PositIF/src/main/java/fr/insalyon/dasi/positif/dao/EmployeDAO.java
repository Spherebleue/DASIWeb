/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Employe;
import fr.insalyon.dasi.positif.metier.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author fmutin
 */
public class EmployeDAO extends PersonneDAO {
    
    public static Employe obtenir(long ID) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Employe.class, ID);
    }
    
    public static void modifier(Employe e) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.merge(e);
    }
    
    public static List<Employe> obtenirTous() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.createQuery("SELECT e FROM Employe e").getResultList();
    }
    
    public static Employe obtenirEmployePourVoyance(Medium medium) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query q = em.createQuery("SELECT e" +
                " FROM Employe e LEFT JOIN e.conversations c" +
                " WHERE :medium MEMBER OF e.mediums AND e.disponible = TRUE"+
                " GROUP BY e" +
                " ORDER BY count(c.ID) ASC").setParameter("medium", medium);
        List l = q.getResultList();
        if(l.size() > 0)
            return (Employe) l.get(0);
        return null;
    }
    
    public static Conversation obtenirConversationEnAttente(Employe employe){
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            Query q = em.createQuery("SELECT c" +
                " FROM Employe e LEFT JOIN e.conversations c" +
                " WHERE e = :employe AND c.heureDebut IS NULL AND c.heureFin IS NULL").setParameter("employe", employe);
            return (Conversation) q.getSingleResult();
        } catch(Exception e) {
            return null;
        }     
    }
}
