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
    public void execute(PrintWriter out, Conversation conv, String qui) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonConv = new JsonObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String date;
         Medium m = conv.getMedium();
        if(conv.getHeureDebut()!=null){
           date =sdf.format(conv.getHeureDebut());
            jsonConv.addProperty("HeureDebut",date);
            if(conv.getHeureFin()!=null){
                date = sdf.format(conv.getHeureFin());
            }            
            jsonConv.addProperty("HeureFin", date);
            jsonConv.addProperty("NomMedium",m.getNom());
             if(m instanceof Astrologue){
                jsonConv.addProperty("Specialite", "Astrologue");
            } else if (m instanceof Tarologue) {
                jsonConv.addProperty("Specialite", "Tarologue");
            } else {
                jsonConv.addProperty("Specialite", "Voyant");
            }
        }else if(conv.getHeureFin()!=null){
            date =sdf.format(conv.getHeureFin());
            jsonConv.addProperty("HeureDebut",date);
            jsonConv.addProperty("HeureFin", date);
            
            jsonConv.addProperty("NomMedium",m.getNom());
            if(m instanceof Astrologue){
                jsonConv.addProperty("Specialite", "Astrologue");
            } else if (m instanceof Tarologue) {
                jsonConv.addProperty("Specialite", "Tarologue");
            } else {
                jsonConv.addProperty("Specialite", "Voyant");
            }
        }else{
            
        }
        
        if(qui.equals("Employe")){
                      jsonConv.addProperty("Commentaire",conv.getCommentaire());
  
        }
        
        
        JsonObject container = new JsonObject();
        container.add("conv",jsonConv);
        out.println(gson.toJson(container));
    }
}
