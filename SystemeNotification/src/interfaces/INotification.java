package interfaces;

import model.Employe;
import model.Message;

public interface INotification {
    void notifier(Employe destinataire, String message);


}
