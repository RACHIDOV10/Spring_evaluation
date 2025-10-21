package presentation;

import dao.IDao;
import entities.Categorie;
import entities.Commande;
import entities.CommandeProduit;
import entities.Produit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateUtil;

import java.util.Date;

public class Presentation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateUtil.class);

        IDao<Categorie> categorieDao = (IDao<Categorie>) context.getBean("categorieService");
        IDao<Commande> commandeDao = (IDao<Commande>) context.getBean("commandeService");
        IDao<Produit> produitDao = (IDao<Produit>) context.getBean("produitService");
        IDao<CommandeProduit> commandeproduitDao = (IDao<CommandeProduit>) context.getBean("commandeProduitService");

        // --- Création et sauvegarde de la catégorie ---
        Categorie categorie = new Categorie("Code1", "Catégorie 1");
        categorieDao.create(categorie);

        // --- Création et sauvegarde des produits ---
        Produit p1 = new Produit("ES12", 120, categorie);
        Produit p2 = new Produit("ZR85", 100, categorie);
        Produit p3 = new Produit("EE85", 200, categorie);
        produitDao.create(p1);
        produitDao.create(p2);
        produitDao.create(p3);

        // --- Création et sauvegarde de la commande ---
        Commande commande = new Commande();
        commande.setDate(new Date());
        commandeDao.create(commande);

        // --- Liaison commande-produits avec quantité ---
        CommandeProduit cp1 = new CommandeProduit(commande, p1, 7);
        CommandeProduit cp2 = new CommandeProduit(commande, p2, 14);
        CommandeProduit cp3 = new CommandeProduit(commande, p3, 5);

        // Persister chaque liaison
        commandeproduitDao.create(cp1);
        commandeproduitDao.create(cp2);
        commandeproduitDao.create(cp3);

        // Ajouter les CommandeProduit à la commande pour affichage
        commande.getCommandeProduits().add(cp1);
        commande.getCommandeProduits().add(cp2);
        commande.getCommandeProduits().add(cp3);

        // --- Affichage ---
        System.out.println("Commande : " + commande.getId() + "     Date : " + commande.getDate());
        System.out.println("Liste des produits :");
        System.out.printf("%-10s %-8s %-10s%n", "Référence", "Prix", "Quantité");
        for (CommandeProduit cp : commande.getCommandeProduits()) {
            System.out.printf("%-10s %-8s %-10s%n",
                    cp.getProduit().getRef(),
                    cp.getProduit().getPrix() + " DH",
                    cp.getQuantite());
        }
    }
}