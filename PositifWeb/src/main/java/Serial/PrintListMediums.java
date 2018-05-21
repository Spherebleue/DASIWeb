/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.positif.metier.Astrologue;
import fr.insalyon.dasi.positif.metier.Medium;
import fr.insalyon.dasi.positif.metier.Tarologue;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author mduraffour
 */
public class PrintListMediums { 
    public void execute(PrintWriter out, Medium medium, List<Medium> mediums) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonMedium = new JsonObject();
        jsonMedium.addProperty("id", medium.getID());
        jsonMedium.addProperty("Nom", medium.getNom());
        if(medium instanceof Astrologue){
            jsonMedium.addProperty("Specialite", "Astrologue");
          
        } else if (medium instanceof Tarologue) {
            jsonMedium.addProperty("Specialite", "Tarologue");
        } else {
            jsonMedium.addProperty("Specialite", "Voyant");
            
        }
        
        JsonObject container = new JsonObject();
        container.add("mediumJour", jsonMedium);
        
        JsonArray jsonListe = new JsonArray();
        for(Medium m : mediums) {
            JsonObject jsonMedium2 = new JsonObject();
            jsonMedium2.addProperty("id", m.getID());
            jsonMedium2.addProperty("Nom", m.getNom());
            if(m instanceof Astrologue){
                jsonMedium2.addProperty("Specialite", "Astrologue");
            } else if (m instanceof Tarologue) {
                jsonMedium2.addProperty("Specialite", "Tarologue");
            } else {
                jsonMedium2.addProperty("Specialite", "Voyant");
            }
            jsonListe.add(jsonMedium2);
        }
       
        container.add("liste", jsonListe);
        
        out.println(gson.toJson(container));
    }
    
}
