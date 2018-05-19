/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Spherebleue
 */
public class ActionAfficherProfil extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Client emp = (Client) session.getAttribute("Personne");
        String signeChinois = emp.getSigneChinois();
        request.setAttribute("signeChinois", signeChinois);
        String signeZod = emp.getSigneZodiaque();
        request.setAttribute("signeZod", signeZod);
        String animal = emp.getAnimalTotem();
        request.setAttribute("animal", animal);
        String couleur = emp.getCouleur();
        request.setAttribute("couleur", couleur);
        
    }
    
}
