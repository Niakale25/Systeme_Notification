package org.example;

import java.util.ArrayList;
import java.util.List;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Poste poste;
    private List<String> notificationsRecues = new ArrayList<>();

    public Employe(int id, String nom, String prenom, Poste poste, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.poste = poste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public List<String> getNotificationsRecues() {
        return notificationsRecues;
    }

    public void setNotificationsRecues(List<String> notificationsRecues) {
        this.notificationsRecues = notificationsRecues;
    }
}
