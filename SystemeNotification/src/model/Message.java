
package model;

import java.util.Date;

public class Message {
    public static Object RecipientType;
    private String contenu;
    private Employe expediteur;
    private Date dateEnvoi;

    public Message(String contenu, Employe expediteur) {
        this.contenu = contenu;
        this.expediteur = expediteur;
        this.dateEnvoi = new Date();
    }

    public String getContenu() { return contenu; }
    public Employe getExpediteur() { return expediteur; }
    public Date getDateEnvoi() { return dateEnvoi; }
}
