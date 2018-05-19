/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.positif.metier.Astrologue;
import fr.insalyon.dasi.positif.metier.Medium;
import fr.insalyon.dasi.positif.metier.Tarologue;
import fr.insalyon.dasi.positif.metier.Voyant;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author mduraffour
 */
public class PrintBio {

    public void execute(PrintWriter out, Medium medium) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonMedium = new JsonObject();
        jsonMedium.addProperty("id", medium.getID());
        jsonMedium.addProperty("Nom", medium.getNom());
        if(medium instanceof Astrologue){
            jsonMedium.addProperty("Specialite", "Astrologue");
            Astrologue astro=(Astrologue)medium;
            jsonMedium.addProperty("Ecole", astro.getEcole());
            jsonMedium.addProperty("Promotion", astro.getPromotion());
        } else if (medium instanceof Tarologue) {
            jsonMedium.addProperty("Specialite", "Tarologue");
            Tarologue taro=(Tarologue)medium;
            jsonMedium.addProperty("Cartes", taro.getCartes());
            
        } else {
            jsonMedium.addProperty("Specialite", "Voyant");
             Voyant voyant=(Voyant)medium;
            jsonMedium.addProperty("Support", voyant.getSupport());
        }
        jsonMedium.addProperty("Bio", medium.getBio());
        
        JsonObject container = new JsonObject();
        container.add("mediumS", jsonMedium);
        out.println(gson.toJson(container));
        
    }
    
}
