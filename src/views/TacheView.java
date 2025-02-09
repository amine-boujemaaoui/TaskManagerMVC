package views;

import java.time.LocalDate;
import java.util.List;

import models.Entities.Tache;

/**
 * Classe responsable de l'affichage des tâches et des interactions utilisateur
 * pour les opérations liées aux tâches.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class TacheView extends AbstractView<Tache> {

    /**
     * Permet à l'utilisateur d'ajouter une nouvelle tâche en saisissant ses
     * informations.
     *
     * @param id L'identifiant unique de la tâche.
     * @return Une instance de {@link Tache} contenant les informations saisies.
     */
    @Override
    public Tache ajouter(int id) {
        String titre = afficherEtLire("Entrez le titre de la tâche :");
        String description = afficherEtLire("Entrez la description de la tâche :");
        LocalDate echeance = LocalDate.parse(afficherEtLire("Entrez l'échéance (AAAA-MM-JJ) :"));
        return new Tache(id, titre, description, echeance, false);
    }

    /**
     * Permet à l'utilisateur de modifier les détails d'une tâche existante.
     *
     * @param tache La tâche à modifier.
     * @return Une instance mise à jour de {@link Tache}.
     */
    @Override
    public Tache modifier(Tache tache) {
        String nouveauTitre = afficherEtLire("Nouveau titre (actuel : " + tache.getTitre() + ") :");
        if (nouveauTitre.isBlank()) {
            nouveauTitre = tache.getTitre();
        }

        String nouvelleDescription = afficherEtLire(
                "Nouvelle description (actuelle : " + tache.getDescription() + ") :");
        if (nouvelleDescription.isBlank()) {
            nouvelleDescription = tache.getDescription();
        }

        String echeanceInput = afficherEtLire(
                "Nouvelle échéance (actuelle : " + tache.getEcheance() + ", format AAAA-MM-JJ) :");
        LocalDate nouvelleEcheance = tache.getEcheance();
        if (!echeanceInput.isBlank()) {
            nouvelleEcheance = LocalDate.parse(echeanceInput);
        }

        String statutInput = afficherEtLire("Statut (true/false, actuel : " + tache.isStatut() + ") :");
        boolean nouveauStatut = tache.isStatut();
        if (!statutInput.isBlank()) {
            nouveauStatut = Boolean.parseBoolean(statutInput);
        }

        return new Tache(tache.getId(), nouveauTitre, nouvelleDescription, nouvelleEcheance, nouveauStatut);
    }

    /**
     * Demande à l'utilisateur l'identifiant d'une tâche à supprimer et affiche un
     * message de confirmation.
     */
    @Override
    public void supprimer() {
        int id = demanderId("supprimer");
        afficherMessage("La tâche avec l'ID " + id + " sera supprimée.");
    }

    /**
     * Affiche la liste de toutes les tâches dans un format tabulaire.
     *
     * @param taches La liste des tâches à afficher.
     */
    @Override
    public void afficherTous(List<Tache> taches) {
        clearConsole();
        String ligneFormat = "│ %-2s │ %-20s │ %-29s │ %-10s │ %-13s │%n";

        System.out
                .println("┌────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-86s │%n", " Liste des tâches (" + taches.size() + ")");
        System.out
                .println("├────┬──────────────────────┬───────────────────────────────┬────────────┬───────────────┤");
        System.out.printf(ligneFormat, "ID", "Titre", "Description", "Échéance", "Statut");
        System.out
                .println("├────┼──────────────────────┼───────────────────────────────┼────────────┼───────────────┤");

        if (taches.isEmpty()) {
            System.out.printf(ligneFormat, "", "", "", "", "");
        } else {
            for (Tache tache : taches) {
                String id = String.format("%2d", tache.getId());
                String titre = tronquer(tache.getTitre(), 20);
                String description = tronquer(tache.getDescription(), 29);
                String echeance = tache.getEcheance() != null ? tache.getEcheance().toString() : "N/A";
                String statut = tache.isStatut() ? "Done" : "In Progress";
                System.out.printf(ligneFormat, id, titre, description, echeance, statut);
            }
        }

        System.out
                .println("└────┴──────────────────────┴───────────────────────────────┴────────────┴───────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...");
    }

    /**
     * Affiche les détails d'une tâche spécifique dans un format lisible.
     *
     * @param tache La tâche dont les détails doivent être affichés.
     */
    @Override
    public void afficherDetails(Tache tache) {
        clearConsole();
        int largeurDescription = 37;

        System.out.println("┌────┬────────────────────────────────────┐");
        System.out.printf("│ %-2d │ %-34s │%n", tache.getId(), tache.getTitre());
        System.out.println("├────┴───────┬────────────────────────────┤");
        System.out.printf("│ Échéance   │ %-26s │%n",
                tache.getEcheance() != null ? tache.getEcheance().toString() : "N/A");
        System.out.printf("│ Statut     │ %-26s │%n", tache.isStatut() ? "Done" : "In Progress");
        System.out.println("├────────────┴────────────────────────────┤");

        String description = tache.getDescription();
        List<String> lignesDescription = decouperEnLignes(description, largeurDescription);
        System.out.println("│ Description                             │");
        for (String ligne : lignesDescription) {
            System.out.printf("│ %-39s │%n", ligne);
        }

        System.out.println("└─────────────────────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...");
    }

    /**
     * Affiche le menu des tâches et lit le choix de l'utilisateur.
     *
     * @return Le choix de l'utilisateur sous forme de chaîne.
     */
    @Override
    public String afficherMenuEtLireChoix() {
        clearConsole();
        String[] options = {
                "1. Ajouter une tâche",
                "2. Modifier une tâche",
                "3. Supprimer une tâche",
                "4. Afficher toutes les tâches",
                "5. Afficher une tâche",
                "0. Quitter"
        };

        afficherMenu("Menu Tâche", options);
        return afficherEtLire("Choisissez une option :");
    }

    /**
     * Demande à l'utilisateur de saisir l'identifiant d'une tâche pour une action
     * spécifique.
     *
     * @param action L'action à effectuer (par ex., "modifier", "supprimer").
     * @return L'identifiant de la tâche saisi par l'utilisateur.
     */
    @Override
    public int demanderId(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID de la tâche pour " + action + " :"));
    }

    /**
     * Méthode appelée pour notifier les observateurs d'une mise à jour.
     * Elle est vide ici, mais peut être implémentée selon les besoins.
     */
    @Override
    public void actualiser() {
    }
}
