package service;

import interfaces.INotification;
import model.Employe;

public class NotificationConsole implements INotification {

    @Override
    public void notifier(Employe destinataire, String message) {
        System.out.println("Console Notification pour " + destinataire.getNomComplet() + " : " + message);
    }
}
