package fr.insalyon.dasi.positif.metier;

import fr.insalyon.dasi.positif.metier.Conversation;
import fr.insalyon.dasi.positif.metier.Medium;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-15T14:41:50")
@StaticMetamodel(Employe.class)
public class Employe_ extends Personne_ {

    public static volatile SingularAttribute<Employe, String> telephone;
    public static volatile ListAttribute<Employe, Medium> mediums;
    public static volatile ListAttribute<Employe, Conversation> conversations;
    public static volatile SingularAttribute<Employe, Boolean> disponible;

}