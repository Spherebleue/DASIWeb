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
import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Medium;
import fr.insalyon.dasi.positif.metier.Tarologue;
import fr.insalyon.dasi.positif.metier.Voyant;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Spherebleue
 */
public class PrintClient {
    
    public void execute(PrintWriter out, Conversation conv) {
        
        Client clt=conv.getClient();
        Medium medium=conv.getMedium();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        JsonObject jsonConversation= new JsonObject();
        
        JsonArray jsonClient = new JsonArray();
        
        JsonObject jsonAdresse = new JsonObject();
        jsonAdresse.addProperty("valeur", clt.getAdresse());
        jsonAdresse.addProperty("nom", "Adresse mail");
        jsonClient.add(jsonAdresse);
        
        JsonObject jsonNom = new JsonObject();
        jsonNom.addProperty("valeur", clt.getNom());
        jsonNom.addProperty("nom", "Nom");
        jsonClient.add(jsonNom);
        
        JsonObject jsonPrenom = new JsonObject();
        jsonPrenom.addProperty("nom", "Prénom");
        jsonPrenom.addProperty("valeur", clt.getPrenom());
        jsonClient.add(jsonPrenom);
        
        JsonObject jsonCivilité = new JsonObject();
        jsonCivilité.addProperty("nom", "Civilité");
        jsonCivilité.addProperty("valeur", clt.getCivilite());
        jsonClient.add(jsonCivilité);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JsonObject jsonDate = new JsonObject();
        jsonDate.addProperty("nom", "DateNaissance");
        jsonDate.addProperty("valeur", sdf.format((Date) clt.getDateNaissance()));
        jsonClient.add(jsonDate);
        
        JsonObject jsonSigneZ = new JsonObject();
        jsonSigneZ.addProperty("nom", "Signe du Zodiaque");
        jsonSigneZ.addProperty("valeur", clt.getSigneZodiaque());
        jsonClient.add(jsonSigneZ);
        
        JsonObject jsonSigneC = new JsonObject();
        jsonSigneC.addProperty("nom", "Signe Chinois");
        jsonSigneC.addProperty("valeur", clt.getSigneChinois());
        jsonClient.add(jsonSigneC);
        
        JsonObject jsonCouleur = new JsonObject();
        jsonCouleur.addProperty("nom", "Couleur porte-bonheur");
        jsonCouleur.addProperty("valeur", clt.getCouleur());
        jsonClient.add(jsonCouleur);
        
        JsonObject jsonAnimal = new JsonObject();
        jsonAnimal.addProperty("nom", "Animal Totem");
        jsonAnimal.addProperty("valeur", clt.getAnimalTotem());
        jsonClient.add(jsonAnimal);
        
        jsonConversation.add("listeProprieteClt", jsonClient);
        
        JsonObject jsonMedium = new JsonObject();
        jsonMedium.addProperty("nom", medium.getNom());
        if(medium instanceof Astrologue){
            jsonMedium.addProperty("Specialite", "Astrologue");
        } else if (medium instanceof Tarologue) {
            jsonMedium.addProperty("Specialite", "Tarologue");
        } else {
            jsonMedium.addProperty("Specialite", "Voyant");
        }  
        
        jsonConversation.add("medium",jsonMedium);
        
        JsonObject container = new JsonObject();
        container.add("Conversation", jsonConversation);
        out.println(gson.toJson(container));
        
    }
}
