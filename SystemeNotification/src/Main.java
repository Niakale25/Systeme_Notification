import interfaces.INotification;
import model.Employe;
import model.GestionnaireEmploye;
import model.Message;
import model.Role;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionnaireEmploye gestionEmploye = new GestionnaireEmploye();
        GestionnaireAbonnement gestionAbonnement = new GestionnaireAbonnement();

        // Employés existants pour les tests
        Employe e1 = new Employe(1, "DIAKITE", "Niakalé", "niakidiaki@gmail.com",Role.EMPLOYE);
        Employe e2 = new Employe(2, "DIAWARA", "Fatoumata", "elinkalika@gmail.com",Role.EMPLOYE);
        Employe e3 = new Employe(3, "Bagayoko", "Amadou", "abagayoko304@gmail.com",Role.EMPLOYE);
        gestionEmploye.ajouterEmploye(e1);
        gestionEmploye.ajouterEmploye(e2);
        gestionEmploye.ajouterEmploye(e3);
        // Modes de notifications disponibles
        List<INotification> modes = new ArrayList<>();
        modes.add(new NotificationConsole());
        modes.add(new NotificationEmail());

        GestionnaireNotification gestionNotif = new GestionnaireNotification(modes, gestionAbonnement);

        while (true) { // boucle infinie pour revenir au menu de connexion
            System.out.println("\n=== Menu Connexion ===");
            System.out.println("1. Gestionnaire");
            System.out.println("2. Employé");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int role = sc.nextInt();
            sc.nextLine(); // vider le buffer

            if (role == 1) {
                menuGestionnaire(sc, gestionEmploye); // va dans le menu gestionnaire
            } else if (role == 2) {
                System.out.println("Choisir un employé par ID :");
                gestionEmploye.listerEmployes().forEach(e -> System.out.println(e.getId() + " : " + e.getNomComplet()));
                int id = sc.nextInt();
                sc.nextLine(); // vider le buffer
                Employe courant = gestionEmploye.listerEmployes().stream()
                        .filter(e -> e.getId() == id)
                        .findFirst()
                        .orElse(null);

                if (courant != null) {
                    menuEmploye(sc, courant, gestionAbonnement, gestionNotif); // va dans le menu employé
                } else {
                    System.out.println("ID invalide.");
                }
            } else if (role == 3) {
                System.out.println("Fermeture du programme. Au revoir !");
                break; // quitte la boucle donc le programme
            } else {
                System.out.println("Choix invalide.");
            }
        }

        sc.close();
    }

    // Menu Gestionnaire
    static void menuGestionnaire(Scanner sc, GestionnaireEmploye gestion) {
        int c;
        do {
            System.out.println("\n*** Menu Gestionnaire ***");
            System.out.println("1. Ajouter employé");
            System.out.println("2. Retirer employé");
            System.out.println("3. Lister employés");
            System.out.println("4. Déconnexion");
            System.out.print("Choix : ");
            c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1:
                    System.out.print("ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nom : ");
                    String nom = sc.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = sc.nextLine();
                    System.out.print("Email : ");
                    String mail = sc.nextLine();
                    gestion.ajouterEmploye(new Employe(id, nom, prenom, mail, Role.EMPLOYE));
                    System.out.println("Employé ajouté.");
                    break;
                case 2:
                    System.out.print("ID à retirer : ");
                    int rid = sc.nextInt();
                    sc.nextLine();
                    Employe aRetirer = gestion.listerEmployes().stream()
                            .filter(e -> e.getId() == rid)
                            .findFirst()
                            .orElse(null);
                    if (aRetirer != null) {
                        gestion.retirerEmploye(aRetirer);
                        System.out.println("Employé retiré.");
                    } else {
                        System.out.println("Employé non trouvé.");
                    }
                    break;
                case 3:
                    System.out.println("Liste des employés :");
                    gestion.listerEmployes().forEach(e ->
                            System.out.println(e.getId() + " - " + e.getNomComplet()));
                    break;
                case 4:
                    System.out.println("Déconnexion du gestionnaire...");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } while (c != 4);
    }

    // Menu Employé
    static void menuEmploye(Scanner sc, Employe courant, GestionnaireAbonnement ab, GestionnaireNotification notif) {
        int c;
        do {
            System.out.println("\n*** Menu Employé (" + courant.getNomComplet() + ") ***");
            System.out.println("1. S'abonner");
            System.out.println("2. Se désabonner");
            System.out.println("3. Vérifier abonnement");
            System.out.println("4. Envoyer message");
            System.out.println("5. Afficher notifications reçues");
            System.out.println("6. Déconnexion");
            System.out.print("Choix : ");
            c = sc.nextInt();
            sc.nextLine();

            switch (c) {
                case 1:
                    ab.abonner(courant);
                    System.out.println("Abonné.");
                    break;
                case 2:
                    ab.desabonner(courant);
                    System.out.println("Désabonné.");
                    break;
                case 3:
                    System.out.println(ab.estAbonne(courant) ? "Tu es abonné." : "Pas abonné.");
                    break;
                case 4:
                    System.out.print("Message : ");
                    String txt = sc.nextLine();
                    notif.notifierTous(new Message(txt, courant));
                    break;
                case 5:
                    courant.afficherNotificationsRecues();
                    break;
                case 6:
                    System.out.println("Déconnexion de l'employé...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (c != 6);
    }
}
