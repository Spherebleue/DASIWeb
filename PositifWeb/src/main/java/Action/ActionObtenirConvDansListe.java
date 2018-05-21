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
        Client clt;
        Client clt2;
        String qui=request.getParameter("qui");
        
        if(qui.equals("Client")){
            clt = (Client) session.getAttribute("Personne");
            clt2 = Services.SeConnecter(clt.getAdresse(),clt.getMotDePasse());
        }else{
            Conversation conv = (Conversation) session.getAttribute("Conversation");
            clt2 = conv.getClient();
        }
        
        List<Conversation> list = clt2.getConversations();
        
        String id=(request.getParameter("idConv")).split(" ")[0];
        
        Conversation cherche;
        
        request.setAttribute("reussi",false);
        for(Conversation conv:list)
        {
            if(String.valueOf(conv.getID()) == null ? id == null : String.valueOf(conv.getID()).equals(id))
            {
                cherche=conv;
                request.setAttribute("Conversation",cherche);
                request.setAttribute("reussi",true);
            }
        }
    }
    
}
