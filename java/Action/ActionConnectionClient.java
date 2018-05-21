/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionConnectionClient extends Action {
    
    @Override
    public void execute (HttpServletRequest request){
        String mdp = request.getParameter("mdpClient");
        //insert verification que c'est bien un chiffre
        String login=request.getParameter("loginClient");
        
        Client clt =Services.SeConnecter(login, mdp);
        HttpSession session = request.getSession(true);
        session.setAttribute("Personne", clt);
        request.setAttribute("Personne", clt);
        
    }
    
    
}
