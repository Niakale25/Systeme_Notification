package service;

import model.Employe;
import model.Role;

import java.util.List;
import java.util.Scanner;

public class AuthentificationService {

    private List<Employe> employes; // liste d'employés (fournie par main)
    private final String gestionnaireEmail = "admin@gmail.com"; // Email du gestionnaire
    private final String gestionnaireMotDePasse = "admin123"; // Mot de passe du gestionnaire

    public AuthentificationService(List<Employe> employes) {
        this.employes = employes;
    }

    // Methode d'authentification
    public Employe seConnecter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();

        // Vérifier si c'est le gestionnaire
        if (email.equals(gestionnaireEmail) && motDePasse.equals(gestionnaireMotDePasse)) {
            System.out.println("Connexion réussie en tant que GESTIONNAIRE.");
            return new Employe(0, "Admin", "", email, motDePasse, Role.GESTIONNAIRE);
        }

        // Vérifier si c'est un employé
        for (Employe emp : employes) {
            if (emp.getEmail().equals(email) && emp.getMotDePasse().equals(motDePasse)) {
                System.out.println("Connexion réussie en tant qu'EMPLOYE.");
                return emp;
            }
        }

        System.out.println("Échec de la connexion. Vérifiez vos informations.");
        return null;
    }
}