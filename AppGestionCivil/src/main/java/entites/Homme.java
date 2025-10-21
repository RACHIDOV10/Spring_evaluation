/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Admin
 */
@Entity
@NamedQueries({

@NamedQuery(name = "femmeByHomme" , query = "select m.femme from Mariage m where m.homme= :homme and (m.dateDebut between :d1 and :d2)")

})
public class Homme extends Personne {

    public Homme() { 
    }

    public Homme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }


}