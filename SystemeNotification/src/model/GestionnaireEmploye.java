package model;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireEmploye {
    private List<Employe> employes = new ArrayList<>();

    public void ajouterEmploye(Employe e) {
        employes.add(e);
    }

    public void retirerEmploye(Employe e) {
        employes.remove(e);
    }

    public List<Employe> listerEmployes() {
        return employes;
    }
}
