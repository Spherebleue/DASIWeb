/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.service.Services;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionObtenirConvDansListe extends Action{

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Client clt = (Client) session.getAttribute("Personne");
        Client clt2 = Services.SeConnecter(clt.getAdresse(),clt.getMotDePasse());
        List<Conversation> list = clt2.getConversations();
        Conversation cherche;
        boolean trouve =false;
        for(Conversation conv:list)
        {
            if(conv.getID()==request.getAttribute("idConv"))
            {
                cherche=conv;
                request.setAttribute("Conversation",cherche);
                request.setAttribute("reussi",true);
            }
        }
        request.setAttribute("reussi",false);
    }
    
}
