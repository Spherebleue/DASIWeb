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
import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Medium;
import fr.insalyon.dasi.positif.metier.Tarologue;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 *
 * @author Spherebleue
 */
public class PrintConv {
    public void execute(PrintWriter out, Conversation conv) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonConv = new JsonObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date =sdf.format(conv.getHeureDebut());
        jsonConv.addProperty("HeureDebut",date);
        date = sdf.format(conv.getHeureFin());
        jsonConv.addProperty("HeureFin", date);
        Medium m = conv.getMedium();
        jsonConv.addProperty("NomMedium",m.getNom());
        if(m instanceof Astrologue){
            jsonConv.addProperty("Specialité", "Astrologue");
        } else if (m instanceof Tarologue) {
            jsonConv.addProperty("Specialité", "Tarologue");
        } else {
            jsonConv.addProperty("Specialité", "Voyant");
        }
    }
}
