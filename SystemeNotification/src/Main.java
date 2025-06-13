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

        // Employés existants avec mot de passe
        Employe e1 = new Employe(1, "DIAKITE", "Niakalé", "niakidiaki@gmail.com", "pass1", Role.EMPLOYE);
        Employe e2 = new Employe(2, "DIAWARA", "Fatoumata", "elinkalika@gmail.com", "pass2", Role.EMPLOYE);
        Employe e3 = new Employe(3, "Bagayoko", "Amadou", "abagayoko304@gmail.com", "pass3", Role.EMPLOYE);

        gestionEmploye.ajouterEmploye(e1);
        gestionEmploye.ajouterEmploye(e2);
        gestionEmploye.ajouterEmploye(e3);

        // Service d'authentification
        AuthentificationService authService = new AuthentificationService(gestionEmploye.listerEmployes());

        // Modes de notification
        List<INotification> modes = new ArrayList<>();
        modes.add(new NotificationConsole());
        modes.add(new NotificationEmail());

        GestionnaireNotification gestionNotif = new GestionnaireNotification(modes, gestionAbonnement);

        while (true) {
            System.out.println("\n=== Menu Connexion ===");
            Employe user = authService.seConnecter(); // Utilisation du service d'authentification

            if (user == null) {
                System.out.println("Connexion échouée. Réessayer.");
                continue;
            }

            if (user.getRole() == Role.GESTIONNAIRE) {
                menuGestionnaire(sc, gestionEmploye, gestionAbonnement); // menu gestionnaire
            } else if (user.getRole() == Role.EMPLOYE) {
                menuEmploye(sc, user, gestionAbonnement, gestionNotif); // menu employé
            }
        }
    }

    // Menu Gestionnaire
    static void menuGestionnaire(Scanner sc, GestionnaireEmploye gestion, GestionnaireAbonnement abonnement) {
        int c;
        do {
            System.out.println("\n*** Menu Gestionnaire ***");
            System.out.println("1. Ajouter employé");
            System.out.println("2. Retirer employé");
            System.out.println("3. Lister employés");
            System.out.println("4. Afficher abonnés");
            System.out.println("5. Déconnexion");
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
                    System.out.print("Mot de passe : ");
                    String mdp = sc.nextLine();
                    gestion.ajouterEmploye(new Employe(id, nom, prenom, mail, mdp, Role.EMPLOYE));
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
                    abonnement.afficherAbonnes();
                    break;
                case 5:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } while (c != 5);
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
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (c != 6);
    }
}
