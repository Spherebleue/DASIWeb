/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.service.Services;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Spherebleue
 */
public class ActionPourcVoyance extends Action {
    
    @Override
    public void execute (HttpServletRequest request){
        HashMap histo= Services.ObtenirCamembertVoyancesParEmploye();
        request.setAttribute("Camembert", histo);
    }
    
}