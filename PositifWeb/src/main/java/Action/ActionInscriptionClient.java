/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.service.Services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mduraffour
 */
public class ActionInscriptionClient extends Action {
    
    @Override
    public void execute (HttpServletRequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String mdp = request.getParameter("mdp");
            String mail = request.getParameter("mail");
            char genre = request.getParameter("genre").charAt(0);
            
            Date dateNaissance = sdf.parse(request.getParameter("date"));
            
            Client clt = new Client(nom, prenom, mdp,genre, dateNaissance, mail );
            request.setAttribute("inscriptionReussi",Services.SInscrire(clt));
            
            request.setAttribute("Personne", clt);
            HttpSession session = request.getSession(true);
            session.setAttribute("Personne", clt);
        } catch (ParseException ex) {
            Logger.getLogger(ActionInscriptionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
