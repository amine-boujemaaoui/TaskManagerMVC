package views;

import models.Tache;
import models.ModelObserver;
import java.util.List;

/**
 * Vue pour afficher la liste des tâches.
 */
public class ListeTachesView extends AbstractView implements ModelObserver {

    private List<Tache> taches;

    public ListeTachesView(List<Tache> taches) {
        this.taches = taches;
    }

    @Override
    public void update(List<Tache> nouvellesTaches) {
        this.taches = nouvellesTaches; // Met à jour la liste interne
        display(); // Affiche la liste mise à jour
    }

    @Override
    public void display() {
        int taille = 56;
        int colWidth = (taille - 6) / 2;

        String titre = " Liste des tâches à faire (" + taches.size() + ")";
        String aucun = " Aucune tâche à afficher.";
        String title = " Title";
        String description = " Description";

        System.out.println("┌" + "─".repeat(taille) + "┐");
        System.out.println("│" + titre + " ".repeat(taille - titre.length()) + "│");
        
        System.out.println("├" + "────" + "┬" + "─".repeat(colWidth) + "┬" + "─".repeat(colWidth) + "┤");
        System.out.println("│" + " id " + "│" + title + " ".repeat(colWidth - title.length()) + "│" + description + " ".repeat(colWidth - description.length()) + "│");
        System.out.println("├" + "────" + "┼" + "─".repeat(colWidth) + "┼" + "─".repeat(colWidth) + "┤");

        if (taches.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                System.out.println("│" + "    " + "│" + " ".repeat(colWidth) + "│" + " ".repeat(colWidth) + "│");
            }
        } else { 
            for (int i = 0; i < taches.size(); i++) {
                Tache tache = taches.get(i);
                String id = String.format("%2d", i);
                System.out.println("│ " + id + " │ " + tache.getTitre() + " ".repeat(colWidth - tache.getTitre().length() - 1) + "│ " + tache.getDescription() + " ".repeat(colWidth - tache.getDescription().length() - 1) + "│");
            }
        }

        System.out.println("└" + "────" + "┴" + "─".repeat(colWidth) + "┴" + "─".repeat(colWidth) + "┘");
    }
}
