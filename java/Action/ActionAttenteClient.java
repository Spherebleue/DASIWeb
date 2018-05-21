/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Employe;
import static fr.insalyon.dasi.positif.service.Services.ObtenirVoyanceEnAttente;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionAttenteClient extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Conversation conv = ObtenirVoyanceEnAttente((Employe)session.getAttribute("Personne"));
        request.setAttribute("Conversation", conv);
        session.setAttribute("Conversation", conv);
    }
    
}
