package presentation;

import entities.Employe;
import entities.EmployeTache;
import entities.Projet;
import entities.Tache;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.EmployeService;
import service.EmployeTacheService;
import service.ProjetService;
import service.TacheService;
import util.HibernateUtil;

public class Presentation {

    public static void main(String[] args) throws Exception {

        // --- Initialisation du contexte Spring/Hibernate ---
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateUtil.class);

        EmployeService es = (EmployeService) context.getBean("employeService");
        EmployeTacheService ets = (EmployeTacheService) context.getBean("employeTacheService");
        ProjetService ps = (ProjetService) context.getBean("projetService");
        TacheService ts = (TacheService) context.getBean("tacheService");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // --- Création d'un employé ---
        Employe emp = new Employe();
        emp.setNom("Rachid");
        emp.setPrenom("CHAIBI");
        es.create(emp);

        // --- Création d’un projet ---
        Projet projet = new Projet();
        projet.setNom("Gestion de stock");
        projet.setDateDebut(sdf.parse("14/01/2013"));
        projet.setEmploye(emp);
        ps.create(projet);

        // --- Création des tâches ---
        Tache t1 = new Tache();
        t1.setNom("Analyse");
        t1.setDateDebut(sdf.parse("10/02/2013"));
        t1.setDateFin(sdf.parse("20/02/2013"));
        t1.setPrix(1500);
        t1.setProjet(projet);
        ts.create(t1);

        Tache t2 = new Tache();
        t2.setNom("Conception");
        t2.setDateDebut(sdf.parse("10/03/2013"));
        t2.setDateFin(sdf.parse("15/03/2013"));
        t2.setPrix(2000);
        t2.setProjet(projet);
        ts.create(t2);

        Tache t3 = new Tache();
        t3.setNom("Développement");
        t3.setDateDebut(sdf.parse("10/04/2013"));
        t3.setDateFin(sdf.parse("25/04/2013"));
        t3.setPrix(3500);
        t3.setProjet(projet);
        ts.create(t3);

        // --- Association EmployeTache ---
        EmployeTache et1 = new EmployeTache(emp, t1, t1.getDateDebut(), t1.getDateFin());
        EmployeTache et2 = new EmployeTache(emp, t2, t2.getDateDebut(), t2.getDateFin());
        EmployeTache et3 = new EmployeTache(emp, t3, t3.getDateDebut(), t3.getDateFin());
        ets.create(et1);
        ets.create(et2);
        ets.create(et3);

        // --- Affichage ---
        SimpleDateFormat sdfAff = new SimpleDateFormat("dd MMMM yyyy");
        System.out.printf("Projet : %-5d Nom: %-15s   Date début: %s%n",
                projet.getId(), projet.getNom(), sdfAff.format(projet.getDateDebut()));

        System.out.println("Liste des tâches :");
        System.out.printf("%-5s %-15s %-20s %-20s%n",
                "Num", "Nom", "Date Début Réelle", "Date Fin Réelle");

        List<EmployeTache> listeTaches = Arrays.asList(et1, et2, et3);
        for (EmployeTache et : listeTaches) {
            System.out.printf("%-5d %-15s %-20s %-20s%n",
                    et.getTache().getId(),
                    et.getTache().getNom(),
                    sdf.format(et.getDateDebutRelle()),
                    sdf.format(et.getDateFinRelle()));
        }

        System.out.println("\nDonnées insérées et affichées avec succès !");
    }
}
