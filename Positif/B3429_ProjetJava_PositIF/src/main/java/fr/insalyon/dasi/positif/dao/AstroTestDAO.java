/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.metier.AstroTest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author flori
 */
public class AstroTestDAO {
    
    final private static String MA_CLÉ_ASTRO_API = "ASTRO-01-M0lGLURBU0ktQVNUUk8tQjAx";
    
    public static List<String> getProfil(String prenom, Date dateNaissance) {
        AstroTest astroTest = new AstroTest(MA_CLÉ_ASTRO_API);
        try {
            List profil = astroTest.getProfil(prenom,dateNaissance);
            return profil;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public static List<String> getPredictions(String couleur, String animal, int amour, int sante, int travail) {
        AstroTest astroTest = new AstroTest(MA_CLÉ_ASTRO_API);
        try {
            List predictions = astroTest.getPredictions(couleur,animal,amour,sante,travail);
            return predictions;
        } catch (IOException ex) {
            return null;
        }
    }
}
