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

/**
 *
 * @author Spherebleue
 */
public class ActionObtenirConv extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Client clt = (Client) request.getAttribute("Personne");
        List<Conversation> list = clt.getConversations();
        request.setAttribute("histo",list);
    }
    
}
