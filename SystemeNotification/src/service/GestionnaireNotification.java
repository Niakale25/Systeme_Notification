package service;

import interfaces.IGestionnaireNotification;
import interfaces.INotification;
import model.Employe;
import model.Message;
import java.util.List;

public class GestionnaireNotification implements IGestionnaireNotification {
    private List<INotification> modes;
    private GestionnaireAbonnement abonnement;

    public GestionnaireNotification(List<INotification> modes, GestionnaireAbonnement abonnement) {
        this.modes = modes;
        this.abonnement = abonnement;
    }

    public void notifierTous(Message message) {
        for (Employe e : abonnement.getAbonnes()) {
            if (!e.equals(message.getExpediteur())) { // éviter d'envoyer à l'expéditeur
                for (INotification notif : modes) {
                    String contenu = "Message de " + message.getExpediteur().getNomComplet() + " : " + message.getContenu();
                    notif.notifier(e, contenu); // On envoie bien : destinataire + contenu texte
                }
                // Ajouter la notification dans l'historique des notifications de l'employé
                e.ajouterNotification(message);
            }
        }
    }

}
