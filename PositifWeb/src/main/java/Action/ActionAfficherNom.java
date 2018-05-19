/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Personne;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionAfficherNom extends Action {
    
    @Override
    public void execute (HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Personne emp = (Personne) session.getAttribute("Personne");
        String prenom = emp.getPrenom();
        request.setAttribute("prenom", prenom);
    }
    
}
