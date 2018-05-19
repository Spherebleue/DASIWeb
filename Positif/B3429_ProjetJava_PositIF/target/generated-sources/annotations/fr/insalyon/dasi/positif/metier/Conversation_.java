package fr.insalyon.dasi.positif.metier;

import fr.insalyon.dasi.positif.metier.Client;
import fr.insalyon.dasi.positif.metier.Employe;
import fr.insalyon.dasi.positif.metier.Medium;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-15T14:41:50")
@StaticMetamodel(Conversation.class)
public class Conversation_ { 

    public static volatile SingularAttribute<Conversation, Employe> employe;
    public static volatile SingularAttribute<Conversation, Date> heureDebut;
    public static volatile SingularAttribute<Conversation, Client> client;
    public static volatile SingularAttribute<Conversation, Long> ID;
    public static volatile SingularAttribute<Conversation, Medium> medium;
    public static volatile SingularAttribute<Conversation, Date> heureFin;
    public static volatile SingularAttribute<Conversation, String> commentaire;

}