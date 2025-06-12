package interfaces;

import model.Employe;
import java.util.List;

public interface IGestionnaireAbonnement {
    void abonner(Employe employe);
    void desabonner(Employe employe);
    boolean estAbonne(Employe employe);
    List<Employe> getAbonnes();
}

