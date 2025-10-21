package entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CommandeProduit {
    @EmbeddedId
    private CommandeProduitPK pk;

    private int quantite;

    @ManyToOne
    @JoinColumn(name="commande",insertable = false, updatable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(name="produit",insertable = false, updatable = false)
    private Produit produit;



    public CommandeProduit() {
    }

    public CommandeProduit(Commande commande,Produit produit,int quantite) {

        this.commande=commande;
        this.produit=produit;
        this.pk = new CommandeProduitPK(commande.getId(),produit.getId());
        this.quantite=quantite;
    }

    public CommandeProduitPK getCommandeProduitPK() {
        return pk;
    }

    public void setCommandeProduitPK(CommandeProduitPK commandeProduitPK) {
        this.pk = commandeProduitPK;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public CommandeProduitPK getPk() {
        return pk;
    }

    public void setPk(CommandeProduitPK pk) {
        this.pk = pk;
    }
}
