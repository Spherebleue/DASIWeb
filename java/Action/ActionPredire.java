/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Personne;
import fr.insalyon.dasi.positif.service.Services;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author moi
 */
public class ActionPredire extends Action {
    
    @Override
    public void execute (HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Conversation conv= (Conversation) session.getAttribute("Conversation");
        Client clt = (Client)conv.getClient();
        long amour =Long.parseLong(request.getParameter("range_amour"));
        long sante=Long.parseLong(request.getParameter("range_sante"));
        long travail=Long.parseLong(request.getParameter("range_travail"));
        List <String> liste_predictions = Services.ObtenirPredictions(clt,(int)amour,(int)sante,(int)travail);
        request.setAttribute("Liste_Predictions",liste_predictions);
        session.setAttribute("Liste_Predictions",liste_predictions);
    }
    
}
