/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;
import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 *
 * @author Admin
 */
@Embeddable
public class MariagePK implements Serializable{
    public int homme;
    public int femme;

    public MariagePK() {
    }

    public MariagePK(int homme, int femme) {
        this.homme = homme;
        this.femme = femme;
    }

    public int getHomme() {
        return homme;
    }

    public void setHomme(int homme) {
        this.homme = homme;
    }

    public int getFemme() {
        return femme;
    }

    public void setFemme(int femme) {
        this.femme = femme;
    }
    
    
    
}
