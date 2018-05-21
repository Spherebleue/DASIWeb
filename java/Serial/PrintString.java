/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.PrintWriter;

/**
 *
 * @author Spherebleue
 */
public class PrintString {
    public void execute(PrintWriter out, String prenom)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonPrenom = new JsonObject();
        jsonPrenom.addProperty("prenom",prenom);
        JsonObject container = new JsonObject();
        container.add("personne", jsonPrenom);
        out.println(gson.toJson(container));
    }
    
}
