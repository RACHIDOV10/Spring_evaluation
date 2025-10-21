/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Admin
 */
@Entity
@NamedNativeQueries({

@NamedNativeQuery(name="enfantFemme" , query = "SELECT SUM(nbrEnfant) FROM mariage WHERE femme = :id AND date BETWEEN :d1 AND :d2")
})

@NamedQueries({

@NamedQuery(name="femmeMarriageCount", query ="SELECT m.femme FROM Mariage m GROUP BY m.femme HAVING COUNT(m.femme) >= :anne")


})
public class Femme extends Personne {

    public Femme() {

    }

    public Femme(String nom, String prenom, String telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
}