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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Spherebleue
 */
public class PrintHashMapFloat {
 
    public void execute(PrintWriter out, HashMap histo)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonArray jsonListe = new JsonArray();
        Set cles = histo.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            String cle = (String) it.next();
            Float valeur = (Float) histo.get(cle); // tu peux typer plus finement ici
            JsonObject jsonDuo = new JsonObject();
            jsonDuo.addProperty("Nom", cle);
            jsonDuo.addProperty("Valeur", valeur);
            jsonListe.add(jsonDuo);
        }
        
        JsonObject container = new JsonObject();
        container.add("duosHisto",jsonListe);
        out.println(gson.toJson(container));
    }
    
    
}
