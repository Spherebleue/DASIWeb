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
        Client clt;
        Client clt2;
        String qui=request.getParameter("qui");
        Boolean b=qui.equals("Client");
        if(b){
            clt = (Client) session.getAttribute("Personne");
        }else{
            Conversation conv = (Conversation) session.getAttribute("Conversation");
            clt = conv.getClient();
        }
        List<Conversation> list = clt.getConversations();
        if(!list.isEmpty())
        {
            request.setAttribute("histo",list);
            request.setAttribute("nonVide",true);
            session.setAttribute("histo",list);
        }
        else
        {
            request.setAttribute("nonVide",false);
        }
    }
    
}
