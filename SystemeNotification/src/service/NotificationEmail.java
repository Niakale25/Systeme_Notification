package service;

import interfaces.INotification;
import model.Employe;
import model.Message;

public class NotificationEmail implements INotification {

    @Override
    public void notifier(Employe destinataire, String message) {
        String sujet = "Nouvelle Notification";
        String contenu = "Bonjour " + destinataire.getNomComplet() + ",\n\n" + message;

        // Appel de la classe EmailSender
        EmailSender.sendEmail(destinataire.getEmail(), sujet, contenu);
    }

}
