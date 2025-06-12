package service;

import interfaces.IGestionnaireAbonnement;
import model.Employe;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireAbonnement implements IGestionnaireAbonnement {
    private List<Employe> abonnes = new ArrayList<>();

    public void abonner(Employe e) {
        if (!abonnes.contains(e)) {
            abonnes.add(e);
        }
    }

    public void desabonner(Employe e) {
        abonnes.remove(e);
    }

    public boolean estAbonne(Employe e) {
        return abonnes.contains(e);
    }

    public List<Employe> getAbonnes() {
        return abonnes;
    }
}

