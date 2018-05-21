/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.service.Services;
import static java.lang.System.out;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionAccepterVoyance extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Conversation conv = (Conversation) session.getAttribute("Conversation");
        Services.AccepterVoyance(conv);
    }
    
}
