package fr.insalyon.dasi.positif.service;

import fr.insalyon.dasi.positif.dao.AstroTestDAO;
import fr.insalyon.dasi.positif.dao.ClientDAO;
import fr.insalyon.dasi.positif.dao.ConversationDAO;
import fr.insalyon.dasi.positif.dao.EmployeDAO;
import fr.insalyon.dasi.positif.dao.Initialisation;
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.dao.MediumDAO;
import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Employe;
import fr.insalyon.dasi.positif.metier.Medium;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Cette classe fournit des méthodes statiques pour accéder au services
 * nécessaire pour le fonctionnement des IHM de Posit'IF.
 * 
 * @author B3429 (Aina RASOLDIER et Florian MUTIN)
 */
public class Services {
    
    /**
     * Cette méthode permet d'inscrit un client passé en paramètre.
     * La méthode se charge également de remplir le profil astrologique du client.
     * @param client Le client à inscrire.
     * @return true si le client a été inscrit, false si quelquechose empêche la création du compte.
     */
    public static boolean SInscrire(Client client) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        List profil = AstroTestDAO.getProfil(client.getPrenom(),client.getDateNaissance());
        
        if(profil != null && ClientDAO.obtenir(client.getAdresse()) == null) {
            client.setSigneZodiaque((String) profil.get(0));
            client.setSigneChinois((String) profil.get(1));
            client.setCouleur((String) profil.get(2));
            client.setAnimalTotem((String) profil.get(3));
            ClientDAO.creer(client);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            
            envoyerMail("contact@posit.if",client.getAdresse(), "Bienvenue chez Posit'IF", "Bonjour "+client.getPrenom()+",\nNous vous confirmons votre inscription au service Posit'IF. Votre numéro de client est : " + client.getID() + ".");
            return true;
        } else {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            
            envoyerMail("contact@posit.if",client.getAdresse(), "Bienvenue chez Posit'IF", "Bonjour "+client.getPrenom()+",\nVotre inscription au service Posit'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
            return false;
        }
    }
    
    /**
     * Cette méthode permet à un client de connecter.
     * @param adresse L'adresse mail du client.
     * @param motDePasse Le mot de passe du client.
     * @return Le client qui correspond à l'adresse mail et au mot de passe fournis, null sinon.
     */
    public static Client SeConnecter(String adresse, String motDePasse){
        JpaUtil.creerEntityManager();
        Client client = ClientDAO.obtenir(adresse);
        JpaUtil.fermerEntityManager();
        if(client != null && client.getMotDePasse().equals(motDePasse)) {
            return client;
        }
        return null;
    }
        
    /**
     * Cette méthode permet à un employé de se connecter.
     * @param identifiant L'identifiant de l'employé.
     * @param motDePasse  Le mot de passe de l'employé.
     * @return L'employé qui correspond à l'identifiant et au mot de passe fournis, null sinon.
     */
    public static Employe SeConnecter(long identifiant, String motDePasse){
        JpaUtil.creerEntityManager();
        Employe employe = EmployeDAO.obtenir(identifiant);
        if(employe != null && employe.getMotDePasse().equals(motDePasse)) {
            JpaUtil.fermerEntityManager();
            return employe;
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    /**
     * Cette méthode permet d'obtenir le médium du jour.
     * @return Le médium du jour.
     */
    public static Medium ObtenirMediumDuJour() {
        JpaUtil.creerEntityManager();
        List<Medium> tousLesMediums = MediumDAO.obtenirTous();
        JpaUtil.fermerEntityManager();
        int position = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) % tousLesMediums.size();
        return tousLesMediums.get(position);
    }
    
    /**
     * Cette méthode permet d'obtenir une liste de tout les médiums de Posit'IF.
     * @return La liste de tout les médiums de Posit'IF.
     */
    public static List<Medium> ObtenirTousMediums() {
        JpaUtil.creerEntityManager();
        List<Medium> listeMediums = MediumDAO.obtenirTous();
        JpaUtil.fermerEntityManager();
        return listeMediums;
    }
    
    /**
     * Cette méthode permet d'initialiser la base de donnée.
     * Elle y ajoute les Médiums et les Employés fournis dans le sujet.
     * Elle indique également quels employés peuvent jouer quels médiums.
     */
    public static void Initialisation() {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Initialisation.Initialiser();
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Cette méthode permet à un client de demander une voyance avec un médium.
     * @param client Le client qui demande la voyance.
     * @param medium Le médium qui est demandé par le médium.
     * @return La conversation dans laquelle va s'effectuer la voyance, null si le médium (ou les employés) n'est pas disponible.
     */
    public static Conversation DemanderVoyance(Client client, Medium medium)
    {
        JpaUtil.creerEntityManager();
        
        Employe employe = EmployeDAO.obtenirEmployePourVoyance(medium);
        
        if(employe == null)
            return null;
        
        employe.setDisponible(false);
        Conversation conversation = new Conversation(employe, medium, client);
        
        JpaUtil.ouvrirTransaction();
        ConversationDAO.creer(conversation);
        EmployeDAO.modifier(employe);
        MediumDAO.modifier(medium);
        ClientDAO.modifier(client);
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        envoyerSMS(employe.getTelephone() + '(' + employe.getPrenom() + ' ' + employe.getNom() + " #" + employe.getID() + ')',"Voyance demandée pour client " + client.getPrenom() + ' ' + client.getNom() + " (#" + client.getID() + "), Médium : " + medium.getNom());
        
        return conversation;
    }
    
    /**
     * Cette méthode permet à un employé d'accepter une une demande de voyance.
     * @param conversation La conversation à travers laquelle va se faire la voyance.
     */
    public static void AccepterVoyance(Conversation conversation)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        conversation.setHeureDebut(new Date());
        ConversationDAO.modifier(conversation);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Cette méthode permet à un employé ou un client de terminer une session de voyance.
     * @param conversation La conversation par laquelle c'est faite la voyance.
     */
    public static void TerminerVoyance(Conversation conversation)
    {
        Date fin = new Date();
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        if(conversation.getHeureFin() == null)
            conversation.setHeureFin(fin);
        ConversationDAO.modifier(conversation);
        
        Employe employe = conversation.getEmploye();
        employe.setDisponible(true);
        EmployeDAO.modifier(employe);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Cette méthode permet à un employé d'ajouter une commentaire après une session de voyance.
     * @param conversation La conversation que l'employé souhaite commenter.
     * @param commentaire Le commentaire que l'employé souhaite déposer.
     */
    public static void CommenterVoyance(Conversation conversation, String commentaire)
    {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        conversation.setCommentaire(commentaire);
        ConversationDAO.modifier(conversation);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Cette méthode permet d'obtenir des predictions astrologiques personnalisées.
     * @param client Le client pour lequel on souhaite avoir des prédictions.
     * @param amour Une note en amour de 1 (Il/elle m'a trompé, je l'ai gardé, il/elle m'a largué.) à 4 (J'aime les animaux et ils m'aiment aussi j'en suis sûr.)
     * @param sante Une note en sante de 1 (J'ai un cancer.) à 4 (Je fais un Iron Man en 5 heures facile.)
     * @param travail Une note de 1 (Je viens de perdre mon job et j'en ai refusé un il y a 2 jours.) à 4 (J'ai eu une augmentation de salaire et une baisse du temps de travail.)
     * @return La liste des predictions dans l'ordre suivant (amour, sante, travail) et null si une erreur s'est produite (vive l'improvisation).
     */
    public static List<String> ObtenirPredictions(Client client, int amour, int sante, int travail)
    {
        return AstroTestDAO.getPredictions(client.getCouleur(), client.getAnimalTotem(), amour, sante, travail);
    }
    
    /**
     * Cette méthode permet d'obtenir les valeur de l'histogramme du nombre de voyances par médium.
     * @return Un dictionnaire des couples (clé = nom du medium, valeur = nombre de voyance).
     */
    public static HashMap<String,Integer> ObtenirHistogrammeVoyancesParMedium()
    {
        List<Medium> mediums = ObtenirTousMediums();
        
        HashMap<String,Integer> histogramme = new HashMap<>();
        for(Medium m : mediums){
            String nom = m.getNom();
            Integer nbVoyances = m.getConversations().size();
            histogramme.put(nom, nbVoyances);
        }
        return histogramme;
    }
    
    
    /**
     * Cette méthode permet d'obtenir les valeur de l'histogramme du nombre de voyances par employé.
     * @return Un dictionnaire des couples (clé = prénom et nom de l'employe, valeur = nombre de voyance)
     */
    public static HashMap<String,Integer> ObtenirHistogrammeVoyancesParEmploye()
    {
        JpaUtil.creerEntityManager();
        List<Employe> employes = EmployeDAO.obtenirTous();
        JpaUtil.fermerEntityManager();
        
        HashMap<String,Integer> histogramme = new HashMap<>();
        for(Employe e : employes){
            String nomPrenom = e.getPrenom() + ' ' + e.getNom();
            Integer nbVoyances = e.getConversations().size();
            histogramme.put(nomPrenom, nbVoyances);
        }
        return histogramme;
    }
    
    /**
     * Cette méthode permet d'obtenir les valeur du camembert du pourcentage de voyances par employé.
     * @return Un dictionnaire des couples (clé = prénom et nom de l'employe, valeur = pourcentage de voyance)
     */
    public static HashMap<String,Float> ObtenirCamembertVoyancesParEmploye()
    {
        JpaUtil.creerEntityManager();
        List<Employe> employes = EmployeDAO.obtenirTous();
        JpaUtil.fermerEntityManager();
        
        int totalVoyances = 0;
        for(Employe e : employes){
            totalVoyances += e.getConversations().size();
        }
        
        HashMap<String,Float> camembert = new HashMap<>();
        for(Employe e : employes){
            String nomPrenom = e.getPrenom() + ' ' + e.getNom();
            Integer nbVoyances = e.getConversations().size();
            camembert.put(nomPrenom, (float) nbVoyances/totalVoyances );
        }
        return camembert;
    }
    
    /**
     * Cette méthode permet de savoir si une conversation à été terminée par l'autre interlocuteur.
     * @param conversation La conversation dont on veut savoir si elle est terminée.
     * @return true si la conversation est terminée, false sinon.
     */
    public static boolean EstTerminee(Conversation conversation)
    {
        JpaUtil.creerEntityManager();
        boolean resultat = ConversationDAO.estTerminee(conversation);
        JpaUtil.fermerEntityManager();
        return resultat;
    }
    
    /**
     * Cette méthode permet d'obtenir la conversation de la voyance en attente d'un employé.
     * @param employe L'employé dont on veut savoir la conversation de la voyance en attente.
     * @return La conversation de la voyance en attente s'il y en a une, null sinon.
     */
    public static Conversation ObtenirVoyanceEnAttente(Employe employe)
    {
        JpaUtil.creerEntityManager();
        Conversation conversation = EmployeDAO.obtenirConversationEnAttente(employe);
        JpaUtil.fermerEntityManager();
        return conversation;
    }
    
    // Cette méthode permet de simuler l'envoye d'un mail en console.
    private static void envoyerMail(String expediteur, String pour, String sujet, String corps) {
        System.out.println("Expéditeur : " + expediteur);
        System.out.println("Pour : " + pour);
        System.out.println("Sujet : " + sujet);
        System.out.println("Corps :");
        System.out.println(corps);
    }
    
    // Cette méthode permet de simuler l'envoye d'un sms en console.
    private static void envoyerSMS(String telephone, String message) {
        System.out.println("SMS à " + telephone + " : " + message);
    }
    
    
    /*
     *  ======================
     *  ||   DEMONSTRATION  ||
     *  ======================
     *
     */
    
    /**
     * Cette méthode correspond à la demonstration de l'utilisation des services proposés par la classe.
     * @param args Cet argument est ignoré.
     */
    public static void main(String[] args) {
        JpaUtil.init();
        
        /**/
        System.out.println("\n\n========== INITIALISATION ==========");
        System.out.println("INITIALISATION DE LA BASE DE DONNEES AVEC DES VOYANTS ET DES EMPLOYES");
        Services.Initialisation();
        
        
        System.out.println("\n\n========== CONNEXIONS D'EMPLOYES ==========");
        System.out.println("CONNEXION DE OLENA :");
        System.out.println("RESULTAT = " + Services.SeConnecter(29l, "PUoa"));
        
        System.out.println("\nCONNEXION DE NICOLAS :");
        System.out.println("RESULTAT = " + Services.SeConnecter(6703l, "TAns"));
        
        System.out.println("\nCONNEXION DE CEDRIC :");
        System.out.println("RESULTAT = " + Services.SeConnecter(3745l, "KOcc"));
        
        System.out.println("\nCONNEXION DE ZOUHAIR :");
        System.out.println("RESULTAT = " + Services.SeConnecter(586l, "GXzr"));
        
        System.out.println("\nCONNEXION DE ZOUHAIR (mauvais identifiant) :");
        System.out.println("RESULTAT = " + Services.SeConnecter(0l, "GXzr"));
        
        System.out.println("\nCONNEXION DE ZOUHAIR (mauvais mot de passe) :");
        System.out.println("RESULTAT = " + Services.SeConnecter(586l, "mauvais mot de passe"));
        
        
        System.out.println("\n\n========== INSCRIPTION DE CLIENTS ==========");
        Client jean = new Client("NEYMAR", "Jean", "NRJn", 'M', new Date(78,5,28), "jean.neymar@google.com", "Gémeaux", "Boeuf", "Tortue", "vert");
        System.out.println("INSCRIPTION DE JEAN :");
        System.out.println("RESULTAT = " + Services.SInscrire(jean));
            
        Client claude = new Client("LOST", "Claude", "LTce", 'F', new Date(89,3,7), "claude.lost@google.com", "Verseaux", "Singe", "Chien", "jaune");
        System.out.println("\nINSCRIPTION DE CLAUDE :");
        System.out.println("RESULTAT = " + Services.SInscrire(claude));
            
        Client claudine = new Client("COPIEUSE", "Claudine", "CEce", 'A', new Date(85,7,9), "claude.lost@google.com", "Balance", "Oiseau", "Chat", "orange");
        System.out.println("\nINSCRIPTION DE CLAUDINE (mail idem à celui de claude) :");
        System.out.println("RESULTAT = " + Services.SInscrire(claudine));
        /**/
        
        System.out.println("\n\n========== CONNEXIONS DE CLIENTS ==========");
        System.out.println("CONNEXION DE CLAUDE :");
        Client claudeConnecte = Services.SeConnecter("claude.lost@google.com", "LTce");
        System.out.println("RESULTAT = " + claudeConnecte);
        
        System.out.println("\nCONNEXION DE JEAN :");
        Client jeanConnecte = Services.SeConnecter("jean.neymar@google.com", "NRJn");
        System.out.println("RESULTAT = " + jeanConnecte);
        
        System.out.println("\nCONNEXION DE JEAN (mauvaise adresse) :");
        System.out.println("RESULTAT = " + Services.SeConnecter("mauvaise adresse", "NRJn"));
        
        System.out.println("\nCONNEXION DE JEAN (mauvais mot de passe) :");
        System.out.println("RESULTAT = " + Services.SeConnecter("jean.neymar@google.com", "mauvais mot de passe"));
        
        
        System.out.println("\n\n========== DEMANDES DINFORMATIONS SUR LES MEDIUMS ==========");
        System.out.println("DEMANDE MEDIUM DU JOUR :");
        Medium mediumDuJour = Services.ObtenirMediumDuJour();
        System.out.println("RESULTAT = " + mediumDuJour);
        
        System.out.println("\nDEMANDE TOUT LES MEDIUMS :");
        List mediums = Services.ObtenirTousMediums();
        System.out.println("RESULTAT = " + mediums);
        
        
        System.out.println("\n\n========== DEMANDES DE VOYANCE ==========");
        System.out.println("CLAUDE DEMANDE MEDIUM DU JOUR :");
        Conversation conversationClaude = Services.DemanderVoyance(claudeConnecte, mediumDuJour);
        Employe employeConversationClaude = conversationClaude.getEmploye();
        System.out.println("RESULTAT = " + conversationClaude + '\n');
        
        Conversation conversation;
        do{
            System.out.println("JEAN DEMANDE MEDIUM DU JOUR :");
            conversation = Services.DemanderVoyance(jeanConnecte, mediumDuJour);
            System.out.println("RESULTAT = " + conversation + '\n');
        }
        while(conversation != null);
        
        
        System.out.println("\n========== DEMANDE DE VOYANCE EN ATTENTE (1/2) ==========");
        System.out.println("DEMANDE SE L'EMPLOYE DE LA VOYANCE DE CLAUDE A UNE VOYANCE EN ATTENTE :");
        System.out.println("RESULTAT = " + Services.ObtenirVoyanceEnAttente(employeConversationClaude));
        
        
        System.out.println("\n\n========== ACCEPTATION DE VOYANCE ==========");
        System.out.println("ACCEPTATION DE LA VOYANCE DE CLAUDE :");
        Services.AccepterVoyance(conversationClaude);
        System.out.println("RESULTAT = " + conversationClaude);
        
        
        System.out.println("\n\n========== DEMANDE DE PREDICTIONS POUR UN CLIENT ==========");
        System.out.println("DEMANDE DE PREDICTIONS POUR CLAUDE :");
        System.out.println("RESULTAT = " + Services.ObtenirPredictions(conversationClaude.getClient(), 2, 0, 4));
        
        
        System.out.println("\n\n========== DEMANDER SI UNE VOYANCE EST TERMINEE (1/2) ==========");
        System.out.println("DEMANDE SI LA VOYANCE DE CLAUDE EST TERMINEE :");
        System.out.println("RESULTAT = " + Services.EstTerminee(conversationClaude));
        
        
        System.out.println("\n\n========== FIN DE VOYANCE ==========");
        System.out.println("Attente de 2 secondes...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.hashCode();
        }
        System.out.println("FIN DE LA VOYANCE DE CLAUDE (" + new Date() +") :");
        Services.TerminerVoyance(conversationClaude);
        System.out.println("RESULTAT = " + conversationClaude);
        
        System.out.println("\nAttente de 2 secondes...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.hashCode();
        }
        System.out.println("FIN DE LA VOYANCE DE CLAUDE (" + new Date() +") :");
        Services.TerminerVoyance(conversationClaude);
        System.out.println("RESULTAT = " + conversationClaude);
        
        
        System.out.println("\n\n========== DEMANDER SI UNE VOYANCE EST TERMINEE (2/2) ==========");
        System.out.println("DEMANDE SI LA VOYANCE DE CLAUDE EST TERMINEE :");
        System.out.println("RESULTAT = " + Services.EstTerminee(conversationClaude));
        
        
        System.out.println("\n\n========== DEMANDE DE VOYANCE EN ATTENTE (2/2) ==========");
        System.out.println("DEMANDE SE L'EMPLOYE DE LA VOYANCE DE CLAUDE A UNE VOYANCE EN ATTENTE :");
        System.out.println("RESULTAT = " + Services.ObtenirVoyanceEnAttente(employeConversationClaude));
        
        
        System.out.println("\n\n========== COMMENTER UNE VOYANCE ==========");
        System.out.println("COMMENTER LA VOYANCE DE CLAUDE :");
        Services.CommenterVoyance(conversationClaude,"Claude est complexé par le petit diamètre de son anus.");
        System.out.println("RESULTAT = " + conversationClaude);
        
        
        System.out.println("\n\n========== DEMANDER LES STATISTIQUES ==========");
        System.out.println("DEMANDER L'HISTOGRAMME DE VOYANCES PAR MEDIUM :");
        System.out.println("RESULTAT = " + Services.ObtenirHistogrammeVoyancesParMedium());
        
        System.out.println("\nDEMANDER L'HISTOGRAMME DE VOYANCES PAR EMPLOYE :");
        System.out.println("RESULTAT = " + Services.ObtenirHistogrammeVoyancesParEmploye());
        
        System.out.println("\nDEMANDER LE CAMEMBERT DE VOYANCES PAR EMPLOYE :");
        System.out.println("RESULTAT = " + Services.ObtenirCamembertVoyancesParEmploye());
        
        System.out.println("\n\n");
        
        JpaUtil.destroy();
    }
}
