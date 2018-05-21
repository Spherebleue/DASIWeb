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
import java.util.List;

/**
 *
 * @author moi
 */
public class PrintPredictions {
    public void execute(PrintWriter out, List <String> liste_predictions) {
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonpredictions = new JsonObject();
        jsonpredictions.addProperty("prediction_amour",liste_predictions.get(0));
        jsonpredictions.addProperty("prediction_sante",liste_predictions.get(1));
        jsonpredictions.addProperty("prediction_travail",liste_predictions.get(2));
        
        JsonObject container = new JsonObject();
        container.add("liste_predictions", jsonpredictions);
        out.println(gson.toJson(container));
        
    }
    
}
