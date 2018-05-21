

import Action.Action;
import Action.ActionAccepterVoyance;
import Action.ActionAfficherNom;
import Action.ActionAfficherProfil;
import Action.ActionAttenteClient;
import Action.ActionCommenterVoyance;
import Action.ActionConnectionClient;
import Action.ActionConnectionEmp;
import Action.ActionConversationTerminee;
import Action.ActionDemanderAttributClient;
import Action.ActionDemanderVoyance;
import Action.ActionInscriptionClient;
import Action.ActionListerTousMediums;
import Action.ActionObtenirBio;
import Action.ActionObtenirHistoClient;
import Action.ActionParEmp;
import Action.ActionPourcVoyance;
import Action.ActionPredire;
import Action.ActionTerminerVoyance;
import Action.ActionVoyParMedium;
import Serial.PrintBio;
import Serial.PrintClient;
import Serial.PrintHashMapFloat;
import Serial.PrintHashMapStrInt;
import Serial.PrintListMediums;
import Serial.PrintMedium;
import Serial.PrintPersonne;
import Serial.PrintPredictions;
import Serial.PrintString;
import Serial.PrintProfil;
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Medium;
import static fr.insalyon.dasi.positif.metier.Conversation_.medium;
import fr.insalyon.dasi.positif.metier.Employe;
import fr.insalyon.dasi.positif.metier.Medium;
import fr.insalyon.dasi.positif.metier.Personne;
import fr.insalyon.dasi.positif.service.Services;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mduraffour   
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    
    
     // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String todo=request.getParameter("action");
        Action action;
        
        if(null != todo)
        switch (todo) {
            case "seConnecterEmp":
                action = new ActionConnectionEmp();
                action.execute(request);
                if(request.getAttribute("Personne")!=null)
                {
                    response.setStatus(200);
                    try (PrintWriter out = response.getWriter()) {
                        PrintPersonne PP = new PrintPersonne();
                        PP.execute(out, (Employe) request.getAttribute("Personne"));
                    }
                }else
                {
                    //403 : erreur on refuse de traiter la requete
                    response.setStatus(403);
                }
                break;
                
            case "seConnecterClient":
                action = new ActionConnectionClient();
                action.execute(request);
                if(request.getAttribute("Personne")!=null)
                {
                    try (PrintWriter out = response.getWriter()) {
                        PrintPersonne PP = new PrintPersonne();
                        PP.execute(out, (Client) request.getAttribute("Personne"));
                    }
                    response.setStatus(200);
                }
                else
                {
                    //403 : erreur on refuse de traiter la requete
                    response.setStatus(403);
                }   
                break;
            case "sInscrireClient":
                action = new ActionInscriptionClient();
                action.execute(request);
                boolean inscriptionReussie =(boolean)request.getAttribute("inscriptionReussi");
                if(inscriptionReussie)
                {
                    try (PrintWriter out = response.getWriter()) {
                        
                        PrintPersonne PP = new PrintPersonne();
                        Client nouveauClient = (Client) request.getAttribute("Personne");
                        
                        System.out.println(nouveauClient.getID());
                        PP.execute(out, nouveauClient);
                    }
                    response.setStatus(200);
                }
                else
                {
                    response.setStatus(400);
                }
                break;
            case "afficherNom":
                action = new ActionAfficherNom();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintString PS = new PrintString();
                    PS.execute(out, (String) request.getAttribute("prenom"));
                }       
                response.setStatus(200);
                break;
            case "deconnexion":
                HttpSession session = request.getSession();
                session.invalidate();
                break;
            case "ObtenirTousMediums":
                action = new ActionListerTousMediums();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintListMediums PLM = new PrintListMediums();
                    Medium m = (Medium)request.getAttribute ("medium_jour");
                    List<Medium> lM = (List<Medium>)request.getAttribute ("liste_medium");
                    System.out.println (m.getNom());
                    PLM.execute(out, m, lM);
                }       
                response.setStatus(200);
                break;
            case "voyParMedium":
                action = new ActionVoyParMedium();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintHashMapStrInt PHM = new PrintHashMapStrInt();
                    PHM.execute(out, (HashMap) request.getAttribute("HistoVoyMedium"));
                }       
                response.setStatus(200);
                break;
            case "voyParEmp":
                action = new ActionParEmp();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintHashMapStrInt PHM = new PrintHashMapStrInt();
                    PHM.execute(out, (HashMap) request.getAttribute("HistoVoyEmp"));
                }       
                response.setStatus(200);
                break;
            case "pourcVoyance":
                action = new ActionPourcVoyance();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintHashMapFloat PHM = new PrintHashMapFloat();
                    PHM.execute(out, (HashMap) request.getAttribute("Camembert"));
                }       
                response.setStatus(200);
                break;
                case "ObtenirHistoriqueClient" :
                action= new ActionObtenirHistoClient();
                action.execute(request);
                break;
            case "afficherProfil":
                action = new ActionAfficherProfil();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintProfil PP = new PrintProfil();
                    
                    PP.execute(out, (String) request.getAttribute("signeChinois"), (String) request.getAttribute("signeZod"), (String) request.getAttribute("animal"), (String) request.getAttribute("couleur"));
                }       
                response.setStatus(200);
                break;
	    case "ObtenirBio":
                action = new ActionObtenirBio();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintBio PB = new PrintBio();
                    Medium m = (Medium)request.getAttribute ("medium_selectionne");
                    PB.execute(out, m);
                }       
                response.setStatus(200);
                break;
            case "attenteClient":
                action = new ActionAttenteClient();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    Conversation conv = (Conversation)request.getAttribute("Conversation");
                    if(conv!=null)
                    {
                        PrintPersonne PP = new PrintPersonne();
                        PP.execute(out, (Personne)conv.getClient());
                    }else
                    {
                        PrintString PS = new PrintString();
			PS.execute(out, "pas de client en attente"); 
                    }
		    response.setStatus(200);
                }    
	        response.setStatus(200);
                break;
            case "demandeAttributClient":
                action = new ActionDemanderAttributClient();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintClient PC = new PrintClient();
                    Conversation conv = (Conversation)request.getAttribute ("Conversation");
                    PC.execute(out, conv);
                }  
                response.setStatus(200);
                break;
            case "demanderVoyance":
                action = new ActionDemanderVoyance();
                String nom = request.getParameter("medium");
                //erreur de relation LAZY si dans l'action :( 
                List<Medium> mediums = Services.ObtenirTousMediums();
                Medium m2 = mediums.get(0);
                for(Medium m : mediums) {
                    if(nom.equals(m.getNom())){
                        m2=m;
                    }   
                }
                request.setAttribute("med",m2);
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    Conversation conv = (Conversation) request.getAttribute("Conversation");
                    if (conv!=null)
                    {
                        PrintString PS= new PrintString();
                        PS.execute(out,"DemandeVoyanceTermine");
                        response.setStatus(200);
                    }
                    else
                    {
                        response.setStatus(404);
                    }
                }
                break;
            case "terminerVoyance":
                action = new ActionTerminerVoyance();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintString PS= new PrintString();
                        PS.execute(out,"TerminerVoyanceTermine");
                }  
                response.setStatus(200);
                break;
            case "accepterVoyance":
                action = new ActionAccepterVoyance();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintString PS= new PrintString();
                    PS.execute(out,"AccepterVoyanceTermine");
                }  
                response.setStatus(200);
                break;
            case "commenterVoyance":
                action = new ActionCommenterVoyance();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintString PS= new PrintString();
                    PS.execute(out,"CommenterVoyanceTermine");
                }  
                response.setStatus(200);
                break;
            case "predire":
                action = new ActionPredire();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    PrintPredictions PP= new PrintPredictions();
                    List <String> liste_predictions =(List <String>)request.getAttribute("Liste_Predictions");
                    PP.execute(out,liste_predictions);
                }  
                response.setStatus(200);
                break;
            case "conversationTerminee":
                action = new ActionConversationTerminee();
                action.execute(request);
                try (PrintWriter out = response.getWriter()) {
                    boolean rep = (boolean)request.getAttribute("convTerminee");
                    PrintString PS= new PrintString();
                    if(rep)
                    {
                        PS.execute(out,"Conversation terminée");
                        response.setStatus(200);
                    }else{
                        PS.execute(out,"Conversation en cours");
                        response.setStatus(403);
                    }
                }  
                break;
            default:
                break;
        }  
        
     }

    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.destroy();
    }
        
}
