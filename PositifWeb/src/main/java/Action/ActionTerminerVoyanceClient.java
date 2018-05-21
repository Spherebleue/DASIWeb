/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author moi
 */
public class ActionTerminerVoyanceClient extends Action{
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Conversation conv = (Conversation) session.getAttribute("Conversation");
        
        if(Services.EstTerminee(conv)){
        } else {
            Services.TerminerVoyance(conv);
        }
        
        session.removeAttribute("Conversation");
        request.removeAttribute("Conversation");
    }
    
}
