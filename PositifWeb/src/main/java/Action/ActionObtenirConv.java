/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
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
        if(clt.getConversations()!=null)
        {
            List<Conversation> list = clt.getConversations();
            request.setAttribute("histo",list);
            request.setAttribute("reussi",true);
        }
        request.setAttribute("reussi",false);
    }
    
}
