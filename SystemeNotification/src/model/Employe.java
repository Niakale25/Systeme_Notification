package model;

import java.util.ArrayList;
import java.util.List;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
    private List<Message> notificationsRecues; // Liste des messages reçus

    public Employe(int id, String nom, String prenom, String email, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.notificationsRecues = new ArrayList<>(); // Initialisation de la liste
    }

    public int getId() {
        return id;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", notificationsRecues=" + notificationsRecues +
                '}';
    }

    // Ajouter une notification reçue
    public void ajouterNotification(Message message) {
        notificationsRecues.add(message);
    }

    // Afficher toutes les notifications reçues
    public void afficherNotificationsRecues() {
        if(notificationsRecues.isEmpty()) {
            System.out.println("Aucune notification reçue.");
        } else {
            System.out.println("Notifications reçues par " + getNomComplet() + " :");
            for (Message msg : notificationsRecues) {
                System.out.println("- De : " + msg.getExpediteur().getNomComplet() + " | Contenu : " + msg.getContenu());
            }
        }
    }
}
