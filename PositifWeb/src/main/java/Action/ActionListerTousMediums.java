/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.metier.Medium;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mduraffour
 */
public class ActionListerTousMediums extends Action{
    @Override
    public void execute (HttpServletRequest request){
        Medium medium = Services.ObtenirMediumDuJour();
        List<Medium> mediums = Services.ObtenirTousMediums();
       HttpSession session = request.getSession(true);
        session.setAttribute("medium_jour", medium);
        session.setAttribute("liste_medium",mediums );
        request.setAttribute("medium_jour", medium);
        request.setAttribute("liste_medium",mediums);
    }
}
