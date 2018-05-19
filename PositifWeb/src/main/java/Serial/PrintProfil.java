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
public class PrintProfil {
    public void execute(PrintWriter out, String signeChinois, String signeZod, String animal, String couleur)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonProfil = new JsonObject();
        jsonProfil.addProperty("signeChinois", signeChinois);
        jsonProfil.addProperty("signeZod", signeZod);
        jsonProfil.addProperty("animal", animal);
        jsonProfil.addProperty("couleur", couleur);
        JsonObject container = new JsonObject();
        container.add("profil", jsonProfil);
        out.println(gson.toJson(container));
    }
}
