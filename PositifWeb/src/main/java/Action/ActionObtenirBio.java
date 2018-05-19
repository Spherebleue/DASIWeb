/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Medium;
import fr.insalyon.dasi.positif.service.Services;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mduraffour
 */
public class ActionObtenirBio extends Action{
    @Override
    public void execute (HttpServletRequest request){
        String nom = request.getParameter("nomMedium");
        
       if(nom.indexOf(',')!=-1){
        nom=nom.substring(0,nom.indexOf(',') );
       }
        
        List<Medium> mediums = Services.ObtenirTousMediums();
       HttpSession session = request.getSession(true);
       Medium m2 = mediums.get(0);
       for(Medium m : mediums) {
           if(nom.equals(m.getNom())){
               m2=m;
           }   
       }
      request.setAttribute("medium_selectionne",m2);
    }
    
}
