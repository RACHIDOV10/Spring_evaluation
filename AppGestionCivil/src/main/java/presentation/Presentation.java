package presentation;

import entites.Femme;
import entites.Homme;
import entites.Mariage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.FemmeService;
import service.HommeService;
import service.MariageService;
import util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Presentation {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateUtil.class);

        HommeService hs = (HommeService) context.getBean("hommeService");
        FemmeService fs = (FemmeService) context.getBean("femmeService");
        MariageService ms = (MariageService) context.getBean("mariageService");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // --- Création de l'homme ---
        Homme homme = new Homme();
        homme.setNom("SAFI");
        homme.setPrenom("SAID");
        homme.setAdresse("RABAT");
        homme.setTelephone("0612345678");
        homme.setDateNaissance(sdf.parse("22/12/1980"));
        hs.create(homme);

        // --- Création de femmes ---
        Femme f1 = new Femme("SALIMA", "RAMI", "0611111111", "CASABLANCA", sdf.parse("10/05/1985"));
        Femme f2 = new Femme("AMAL", "ALI", "0622222222", "RABAT", sdf.parse("20/07/1990"));
        Femme f3 = new Femme("WAFA", "ALAOUI", "0633333333", "MARRAKECH", sdf.parse("15/03/1995"));
        Femme f4 = new Femme("KARIMA", "ALAMI", "0644444444", "CASABLANCA", sdf.parse("01/01/1980"));

        fs.create(f1);
        fs.create(f2);
        fs.create(f3);
        fs.create(f4);

        // --- Création des mariages ---
        ms.create(new Mariage(homme, f1, sdf.parse("03/09/1990"), null, 4)); // en cours
        ms.create(new Mariage(homme, f2, sdf.parse("03/09/1995"), null, 2)); // en cours
        ms.create(new Mariage(homme, f3, sdf.parse("04/11/2000"), null, 3)); // en cours
        ms.create(new Mariage(homme, f4, sdf.parse("03/09/1989"), sdf.parse("03/09/1990"), 0)); // échoué

        // --- Affichage ---
        System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());

        // Mariages en cours
        System.out.println("Mariages En Cours :");
        List<Mariage> enCours = ms.mariagesEnCours(homme); // méthode à créer dans MariageService
        int index = 1;
        for (Mariage m : enCours) {
            System.out.printf("%d. Femme : %-15s Date Début : %s  Nbr Enfants : %d%n",
                    index++,
                    m.getFemme().getNom() + " " + m.getFemme().getPrenom(),
                    sdf.format(m.getDateDebut()),
                    m.getNombreEnfants());
        }

        // Mariages échoués
        System.out.println("\nMariages échoués :");
        List<Mariage> echoues = ms.mariagesEchoues(homme); // méthode à créer dans MariageService
        index = 1;
        for (Mariage m : echoues) {
            System.out.printf("%d. Femme : %-15s Date Début : %s  Date Fin : %s  Nbr Enfants : %d%n",
                    index++,
                    m.getFemme().getNom() + " " + m.getFemme().getPrenom(),
                    sdf.format(m.getDateDebut()),
                    sdf.format(m.getDateFin()),
                    m.getNombreEnfants());
        }
    }
}
