/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionDemanderAttributClient extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Conversation conv = (Conversation) session.getAttribute("Conversation");
        Client clt = conv.getClient();
        request.setAttribute("Client",clt);
    }
    
}
