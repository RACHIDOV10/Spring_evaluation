/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Admin
 */
@Entity
public class Mariage {

    @EmbeddedId
    private MariagePK pk;

    @ManyToOne
    @JoinColumn(name = "homme", insertable = false, updatable = false)
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme", insertable = false, updatable = false)
    private Femme femme;

    public Mariage() {
    }

    public Mariage(Homme homme, Femme femme, Date dateDebut, Date dateFin, int nombreEnfants) {
        this.pk=new MariagePK(homme.getId(), femme.getId());
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreEnfants = nombreEnfants;
    }

    private Date dateDebut;
    private Date dateFin;
    private int nombreEnfants;

    public MariagePK getPk() {
        return pk;
    }

    public void setPk(MariagePK pk) {
        this.pk = pk;
    }

    

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(int nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }



}
