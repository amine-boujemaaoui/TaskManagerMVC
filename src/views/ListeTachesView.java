package views;

import models.Tache;
import interfaces.Observateur;
import java.util.List;

/**
 * Vue pour afficher la liste des tâches.
 */
public class ListeTachesView extends AbstractView implements Observateur {

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
        int taille = 90; // Ajuster la largeur totale
        int colIdWidth = 4;
        int colTitleWidth = 20;
        int colDescWidth = 30;
        int colDateWidth = 15;
        int colStatusWidth = 15;

        String titre = " Liste des tâches à faire (" + taches.size() + ")";
        String title = " Title";
        String description = " Description";
        String dateEcheance = " Due Date";
        String status = " Status";

        System.out.println("┌" + "─".repeat(taille - 2) + "┐");
        System.out.println("│" + titre + " ".repeat(taille - titre.length() - 2) + "│");

        System.out.println("├" + "─".repeat(colIdWidth) + "┬" + "─".repeat(colTitleWidth) + "┬"
                + "─".repeat(colDescWidth) + "┬" + "─".repeat(colDateWidth) + "┬" + "─".repeat(colStatusWidth) + "┤");
        System.out.println("│ id │" + title + " ".repeat(colTitleWidth - title.length()) + "│" + description
                + " ".repeat(colDescWidth - description.length()) + "│" + dateEcheance
                + " ".repeat(colDateWidth - dateEcheance.length()) + "│" + status
                + " ".repeat(colStatusWidth - status.length()) + "│");
        System.out.println("├" + "─".repeat(colIdWidth) + "┼" + "─".repeat(colTitleWidth) + "┼"
                + "─".repeat(colDescWidth) + "┼" + "─".repeat(colDateWidth) + "┼" + "─".repeat(colStatusWidth) + "┤");

        if (taches.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                System.out.println(
                        "│" + " ".repeat(colIdWidth) + "│" + " ".repeat(colTitleWidth) + "│" + " ".repeat(colDescWidth)
                                + "│" + " ".repeat(colDateWidth) + "│" + " ".repeat(colStatusWidth) + "│");
            }
        } else {
            for (int i = 0; i < taches.size(); i++) {
                Tache tache = taches.get(i);
                String id = String.format("%2d", i);
                String titreTache = tache.getTitre();
                String descTache = tache.getDescription();
                String echeance = tache.getEcheance() != null ? tache.getEcheance().toString() : "N/A";
                String statut = tache.isStatut() ? "Done" : "In Progress";

                System.out.println(
                        "│ " + id + " │ "
                                + titreTache + " ".repeat(colTitleWidth - titreTache.length() - 1) + "│ "
                                + descTache + " ".repeat(colDescWidth - descTache.length() - 1) + "│ "
                                + echeance + " ".repeat(colDateWidth - echeance.length() - 1) + "│ "
                                + statut + " ".repeat(colStatusWidth - statut.length() - 1) + "│");
            }
        }

        System.out.println("└" + "─".repeat(colIdWidth) + "┴" + "─".repeat(colTitleWidth) + "┴"
                + "─".repeat(colDescWidth) + "┴" + "─".repeat(colDateWidth) + "┴" + "─".repeat(colStatusWidth) + "┘");
    }
}
