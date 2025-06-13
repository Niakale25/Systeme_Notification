package model;

import java.util.ArrayList;
import java.util.List;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse; // Nouveau champ
    private Role role;
    private List<Message> notificationsRecues;

    public Employe(int id, String nom, String prenom, String email, String motDePasse, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.notificationsRecues = new ArrayList<>();
    }

    // Getters
    public int getId() { return id; }
    public String getNomComplet() { return prenom + " " + nom; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public String getMotDePasse() { return motDePasse; } // Getter pour mot de passe

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

    // Ajouter une notification
    public void ajouterNotification(Message message) {
        notificationsRecues.add(message);
    }

    // Afficher les notifications reçues
    public void afficherNotificationsRecues() {
        if (notificationsRecues.isEmpty()) {
            System.out.println("Aucune notification reçue.");
        } else {
            System.out.println("Notifications reçues par " + getNomComplet() + " :");
            for (Message msg : notificationsRecues) {
                System.out.println("- De : " + msg.getExpediteur().getNomComplet() + " | Contenu : " + msg.getContenu());
            }
        }
    }
}
