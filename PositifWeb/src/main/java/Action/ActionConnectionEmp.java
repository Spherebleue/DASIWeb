package Action;



import fr.insalyon.dasi.positif.metier.Employe;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Spherebleue
 */
public class ActionConnectionEmp extends Action {
    
    @Override
    public void execute (HttpServletRequest request){
        String mdp = request.getParameter("mdpEmp");
        //insert verification que c'est bien un chiffre
        long login=Long.parseLong(request.getParameter("loginEmp"));
        HttpSession session = request.getSession(true);
        Employe emp =Services.SeConnecter(login, mdp);
        session.setAttribute("Personne", emp);
        request.setAttribute("Personne", emp);
    }
    
    
}
