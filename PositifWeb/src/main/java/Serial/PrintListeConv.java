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
import fr.insalyon.dasi.positif.metier.Conversation;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Spherebleue
 */
public class PrintListeConv {
    
     public void execute(PrintWriter out, List<Conversation> histo) {
         
         
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonListe = new JsonArray();
        
        for(Conversation conv:histo)
        {
            JsonObject jsonMedium2 = new JsonObject();
            jsonMedium2.addProperty("id", conv.getID());
            jsonMedium2.addProperty("NomMedium", conv.getMedium().getNom());
            Date date= conv.getHeureDebut();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jsonMedium2.addProperty("DateDebut", sdf.format(date));
            jsonListe.add(jsonMedium2);
        }
        
        JsonObject container = new JsonObject();
        container.add("liste", jsonListe);
        out.println(gson.toJson(container));
         
     }
}
