package views;

import models.Tache;

import java.time.LocalDate;
import java.util.List;

public class TacheView extends AbstractView {

    /**
     * Affiche le menu principal et capture le choix de l'utilisateur.
     * 
     * @return Le choix de l'utilisateur.
     */
    public String afficherMenuEtLireChoix() {
        System.out.println("=== MENU GESTION DES TÂCHES ===");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Modifier une tâche");
        System.out.println("3. Supprimer une tâche");
        System.out.println("4. Afficher toutes les tâches");
        System.out.println("0. Quitter");
        return afficherEtLire("Votre choix:");
    }

    /**
     * Saisit les informations d'une nouvelle tâche.
     * 
     * @param id L'identifiant de la nouvelle tâche.
     * @return La nouvelle tâche créée.
     */
    public Tache saisirTache(int id) {
        String titre = afficherEtLire("Titre:");
        String description = afficherEtLire("Description:");
        LocalDate echeance = LocalDate.parse(afficherEtLire("Échéance (AAAA-MM-JJ):"));
        return new Tache(id, titre, description, echeance, false);
    }

    /**
     * Demande l'ID de la tâche à modifier ou supprimer.
     * 
     * @param action Action pour laquelle l'ID est requis.
     * @return L'ID de la tâche.
     */
    public int demanderIdTache(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID de la tâche à " + action + ":"));
    }

    /**
     * Modifie les informations d'une tâche existante.
     * 
     * @param tache La tâche existante à modifier.
     * @return La tâche modifiée.
     */
    public Tache modifierTache(Tache tache) {
        String titre = afficherEtLire("Nouveau titre (actuel: " + tache.getTitre() + "):");
        String description = afficherEtLire("Nouvelle description (actuelle: " + tache.getDescription() + "):");
        LocalDate echeance = LocalDate
                .parse(afficherEtLire("Nouvelle échéance (actuelle: " + tache.getEcheance() + ", format AAAA-MM-JJ):"));
        boolean statut = Boolean.parseBoolean(afficherEtLire("Statut (true/false):"));
        return new Tache(tache.getId(), titre, description, echeance, statut);
    }

    /**
     * Affiche la liste des tâches avec un tableau formaté.
     * 
     * @param taches La liste des tâches à afficher.
     */
    public void afficherTaches(List<Tache> taches) {

        String ligneFormat = "│ %-2s │ %-20s │ %-29s │ %-13s │ %-13s │%n";

        System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-89s │%n", " Liste des tâches à faire (" + taches.size() + ")");
        System.out.println("├────┬──────────────────────┬───────────────────────────────┬───────────────┬───────────────┤");
        System.out.printf(ligneFormat, "ID", "Title", "Description", "Due Date", "Status");
        System.out.println("├────┼──────────────────────┼───────────────────────────────┼───────────────┼───────────────┤");

        if (taches.isEmpty()) {
            System.out.printf(ligneFormat, "", "", "", "", "");
        } else {
            for (int i = 0; i < taches.size(); i++) {
                Tache tache = taches.get(i);
                String id = String.format("%2d", i);
                String titreTache = tache.getTitre();
                String descTache = tache.getDescription();
                String echeance = tache.getEcheance() != null ? tache.getEcheance().toString() : "N/A";
                String statut = tache.isStatut() ? "Done" : "In Progress";
                System.out.printf(ligneFormat, id, titreTache, descTache, echeance, statut);
            }
        }

        System.out.println("└────┴──────────────────────┴───────────────────────────────┴───────────────┴───────────────┘");
    }
}
