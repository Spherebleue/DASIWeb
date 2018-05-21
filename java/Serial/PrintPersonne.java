/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.positif.metier.Personne;
import java.io.PrintWriter;

/**
 *
 * @author Spherebleue
 */
public class PrintPersonne {
    
    public void execute(PrintWriter out, Personne p)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonPersonne = new JsonObject();
        jsonPersonne.addProperty("id", p.getID());
        jsonPersonne.addProperty("motDePasse", p.getMotDePasse());
        jsonPersonne.addProperty("nom", p.getNom());
        jsonPersonne.addProperty("prenom",p.getPrenom());
        
        JsonObject container = new JsonObject();
        container.add("personne", jsonPersonne);
        
        out.println(gson.toJson(container));
    }
    
    
}
