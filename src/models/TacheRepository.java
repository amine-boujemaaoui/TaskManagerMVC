package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Référentiel pour gérer les tâches.
 * Contient la logique métier pour ajouter, supprimer et récupérer les tâches.
 */
public class TacheRepository extends AbstractModel {

    private final List<Tache> taches;

    /**
     * Constructeur pour initialiser la liste des tâches.
     */
    public TacheRepository() {
        this.taches = new ArrayList<>();
    }

    /**
     * Ajoute une tâche au référentiel et notifie les observateurs.
     *
     * @param tache la tâche à ajouter.
     */
    public void ajouterTache(Tache tache) {
        taches.add(tache);
        notifyObservers();
    }

    /**
     * Supprime une tâche du référentiel et notifie les observateurs.
     *
     * @param tache la tâche à supprimer.
     */
    public void supprimerTache(Tache tache) {
        taches.remove(tache);
        notifyObservers();
    }

    /**
     * Retourne la liste de toutes les tâches.
     *
     * @return une liste immuable contenant toutes les tâches.
     */
    public List<Tache> getToutesLesTaches() {
        return new ArrayList<>(taches);
    }
}
