package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Référentiel pour gérer les tâches.
 */
public class TacheRepository extends AbstractModel {

    private final List<Tache> taches;

    public TacheRepository() {
        this.taches = new ArrayList<>();
    }

    public void ajouterTache(Tache tache) {
        taches.add(tache);
        notifyObservers(new ArrayList<>(taches)); // Notifie avec la liste mise à jour
    }

    public void supprimerTache(Tache tache) {
        taches.remove(tache);
        notifyObservers(new ArrayList<>(taches)); // Notifie avec la liste mise à jour
    }

    public List<Tache> getToutesLesTaches() {
        return new ArrayList<>(taches);
    }
}
