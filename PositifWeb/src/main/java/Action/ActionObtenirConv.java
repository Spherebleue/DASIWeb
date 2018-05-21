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
public class ActionObtenirConv extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Client clt = (Client) session.getAttribute("Personne");
        Client clt2 = Services.SeConnecter(clt.getAdresse(),clt.getMotDePasse());
        List<Conversation> list = clt2.getConversations();
        if(list!=null)
        {
            request.setAttribute("histo",list);
            request.setAttribute("reussi",true);
            session.setAttribute("histo",list);
        }
        else
        {
            request.setAttribute("reussi",false);
        }
    }
    
}
