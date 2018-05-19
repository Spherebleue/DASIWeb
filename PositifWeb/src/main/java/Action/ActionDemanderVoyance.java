/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Medium;
import fr.insalyon.dasi.positif.service.Services;
import static fr.insalyon.dasi.positif.service.Services.DemanderVoyance;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionDemanderVoyance extends Action {

    @Override
    public void execute(HttpServletRequest request) {
       HttpSession session = request.getSession();
       Medium m2 = (Medium) request.getAttribute("med");
       Client clt = (Client) session.getAttribute("Personne");
       Conversation conv = DemanderVoyance(clt,m2);
       request.setAttribute("Conversation",conv);
       session.setAttribute("Conversation",conv);
    }
    
}
