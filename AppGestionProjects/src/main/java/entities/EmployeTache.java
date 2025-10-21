package entities;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class EmployeTache {

    @EmbeddedId
    private EmployeTachePK pk;

    @ManyToOne
    @JoinColumn(name = "employe", insertable = false, updatable = false)
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "tache", insertable = false, updatable = false)
    private Tache tache;

    private Date dateDebutRelle;
    private Date dateFinRelle;

    public EmployeTache(Employe employe, Tache tache, Date dateDebutRelle, Date dateFinRelle) {
        this.pk = new EmployeTachePK(employe.getId(), tache.getId());
        this.employe = employe;
        this.tache = tache;
        this.dateDebutRelle = dateDebutRelle;
        this.dateFinRelle = dateFinRelle;
    }

    public EmployeTache() {
    }

    public EmployeTachePK getPk() {
        return pk;
    }

    public void setPk(EmployeTachePK pk) {
        this.pk = pk;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Date getDateDebutRelle() {
        return dateDebutRelle;
    }

    public void setDateDebutRelle(Date dateDebutRelle) {
        this.dateDebutRelle = dateDebutRelle;
    }

    public Date getDateFinRelle() {
        return dateFinRelle;
    }

    public void setDateFinRelle(Date dateFinRelle) {
        this.dateFinRelle = dateFinRelle;
    }

    @Override
    public String toString() {
        return "EmployeTache{" + "pk=" + pk + ", employe=" + employe + ", tache=" + tache + '}';
    }

}