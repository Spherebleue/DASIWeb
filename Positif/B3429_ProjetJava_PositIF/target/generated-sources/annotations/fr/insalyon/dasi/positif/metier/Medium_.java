package fr.insalyon.dasi.positif.metier;

import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Employe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-15T14:41:50")
@StaticMetamodel(Medium.class)
public abstract class Medium_ { 

    public static volatile SingularAttribute<Medium, String> bio;
    public static volatile SingularAttribute<Medium, Long> ID;
    public static volatile SingularAttribute<Medium, String> nom;
    public static volatile ListAttribute<Medium, Conversation> conversations;
    public static volatile ListAttribute<Medium, Employe> employes;

}