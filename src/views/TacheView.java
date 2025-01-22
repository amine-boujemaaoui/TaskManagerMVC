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
     * Affiche la liste des tâches.
     * 
     * @param taches La liste des tâches à afficher.
     */
    public void afficherTaches(List<Tache> taches) {
        if (taches.isEmpty()) {
            afficherMessage("Aucune tâche à afficher.");
        } else {
            System.out.println("=== LISTE DES TÂCHES ===");
            for (Tache tache : taches) {
                afficherMessage("ID: " + tache.getId());
                afficherMessage("Titre: " + tache.getTitre());
                afficherMessage("Description: " + tache.getDescription());
                afficherMessage("Échéance: " + tache.getEcheance());
                afficherMessage("Statut: " + (tache.isStatut() ? "Terminée" : "En cours"));
                System.out.println("--------------------------");
            }
        }
    }
}
