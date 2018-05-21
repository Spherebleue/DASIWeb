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
import java.io.PrintWriter;


/**
 *
 * @author mduraffour
 */
public class PrintMedium {
    public void execute(PrintWriter out, Medium m)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonMedium = new JsonObject();
        jsonMedium.addProperty("id", m.getID());
        jsonMedium.addProperty("Nom", m.getNom());
        if(m instanceof Astrologue){
            jsonMedium.addProperty("Specialité", "Astrologue");
        } else if (m instanceof Tarologue) {
            jsonMedium.addProperty("Specialité", "Tarologue");
        } else {
            jsonMedium.addProperty("Specialité", "Voyant");
        }
        
        JsonObject container2 = new JsonObject();
        container2.add("medium", jsonMedium);
        
        out.println(gson.toJson(container2));
        
        
    }
}
