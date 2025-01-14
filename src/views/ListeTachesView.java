package views;

import models.ModelObserver;
import models.Tache;

import java.util.List;

/**
 * Vue pour afficher la liste des tâches.
 */
public class ListeTachesView extends AbstractView implements ModelObserver {

    private List<Tache> taches;

    /**
     * Constructeur pour initialiser la vue avec une liste de tâches.
     *
     * @param taches la liste des tâches à afficher.
     */
    public ListeTachesView(List<Tache> taches) {
        this.taches = taches;
    }

    /**
     * Affiche toutes les tâches dans la liste.
     */
    @Override
    public void display() {
        System.out.println("\n--- Liste des Tâches ---");
        if (taches.isEmpty()) {
            System.out.println("Aucune tâche disponible.");
        } else {
            for (int i = 0; i < taches.size(); i++) {
                System.out.println((i + 1) + ". " + taches.get(i));
            }
        }
    }

    /**
     * Met à jour la liste des tâches lorsque le modèle change.
     */
    @Override
    public void update() {
        setTaches(taches); // Met à jour la liste interne
        display();         // Affiche la liste mise à jour
    }

    /**
     * Permet de mettre à jour la liste des tâches directement.
     *
     * @param taches la nouvelle liste de tâches.
     */
    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}
