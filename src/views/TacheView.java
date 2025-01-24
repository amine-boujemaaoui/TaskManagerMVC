package views;

import models.Tache;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de l'affichage des tâches et des interactions utilisateur
 * pour les opérations liées aux tâches.
 */
public class TacheView extends AbstractView {

    /**
     * Affiche le menu principal et capture le choix de l'utilisateur.
     * 
     * @return Le choix de l'utilisateur sous forme de chaîne.
     */
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

        afficherMenu("Commandes disponibles", options);

        return afficherEtLire("TacheManager");
    }

    /**
     * Capture les informations nécessaires pour créer une nouvelle tâche.
     * 
     * @param id L'identifiant unique de la tâche.
     * @return Une nouvelle instance de {@link Tache}.
     */
    public Tache saisirTache(int id) {
        String titre = afficherEtLire("Titre:");
        String description = afficherEtLire("Description:");
        LocalDate echeance = LocalDate.parse(afficherEtLire("Échéance (AAAA-MM-JJ):"));
        return new Tache(id, titre, description, echeance, false);
    }

    /**
     * Demande à l'utilisateur de saisir l'ID d'une tâche pour une action donnée.
     * 
     * @param action Une chaîne décrivant l'action (modifier, supprimer, etc.).
     * @return L'ID de la tâche saisi par l'utilisateur.
     */
    public int demanderIdTache(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID de la tâche à " + action + ":"));
    }

    /**
     * Modifie les informations d'une tâche existante sans validation spécifique.
     * 
     * @param tache La tâche à modifier.
     * @return Une nouvelle instance de {@link Tache} contenant les modifications.
     */
    public Tache modifierTacheSansValidation(Tache tache) {
        String titre = afficherEtLire("Nouveau titre (actuel: " + tache.getTitre() + "):");
        if (titre.isBlank()) {
            titre = tache.getTitre();
        }

        String description = afficherEtLire("Nouvelle description (actuelle: " + tache.getDescription() + "):");
        if (description.isBlank()) {
            description = tache.getDescription();
        }

        String echeanceInput = afficherEtLire("Nouvelle échéance (actuelle: " + tache.getEcheance() + ", format AAAA-MM-JJ):");
        LocalDate echeance = tache.getEcheance();
        if (!echeanceInput.isBlank()) {
            echeance = LocalDate.parse(echeanceInput);
        }

        String statutInput = afficherEtLire("Statut (true/false, actuel: " + tache.isStatut() + "):");
        boolean statut = tache.isStatut();
        if (!statutInput.isBlank()) {
            statut = Boolean.parseBoolean(statutInput);
        }

        return new Tache(tache.getId(), titre, description, echeance, statut);
    }

    /**
     * Affiche une liste de tâches dans un tableau formaté.
     * 
     * @param taches La liste des tâches à afficher.
     */
    public void afficherTaches(List<Tache> taches) {
        clearConsole();
        String ligneFormat = "│ %-2s │ %-20s │ %-29s │ %-10s │ %-13s │%n";

        System.out.println("┌────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-86s │%n", " Liste des tâches (" + taches.size() + ")");
        System.out.println("├────┬──────────────────────┬───────────────────────────────┬────────────┬───────────────┤");
        System.out.printf(ligneFormat, "ID", "Title", "Description", "Due Date", "Status");
        System.out.println("├────┼──────────────────────┼───────────────────────────────┼────────────┼───────────────┤");

        if (taches.isEmpty()) {
            System.out.printf(ligneFormat, "", "", "", "", "");
        } else {
            for (Tache tache : taches) {
                String id = String.format("%2d", tache.getId());
                String titreTache = tronquer(tache.getTitre(), 20);
                String descTache = tronquer(tache.getDescription(), 29);
                String echeance = tache.getEcheance() != null ? tache.getEcheance().toString() : "N/A";
                String statut = tache.isStatut() ? "Done" : "In Progress";
                System.out.printf(ligneFormat, id, titreTache, descTache, echeance, statut);
            }
        }

        System.out.println("└────┴──────────────────────┴───────────────────────────────┴────────────┴───────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...", false);
    }

    /**
     * Tronque une chaîne de texte si elle dépasse une certaine longueur et la remplit si nécessaire.
     * 
     * @param texte   Le texte à tronquer.
     * @param largeur La largeur maximale autorisée.
     * @return La chaîne tronquée ou remplie.
     */
    private String tronquer(String texte, int largeur) {
        if (texte.length() > largeur) {
            return texte.substring(0, largeur - 3) + "...";
        }
        return String.format("%-" + largeur + "s", texte);
    }

    /**
     * Affiche les détails d'une tâche avec un format clair.
     * 
     * @param tache La tâche dont les détails doivent être affichés.
     */
    public void afficherDetailsTache(Tache tache) {
        clearConsole();
        int largeurDescription = 37; // Largeur du cadre pour la description

        System.out.println("┌────┬────────────────────────────────────┐");
        System.out.printf("│ %-2d │ %-34s │%n", tache.getId(), tache.getTitre());
        System.out.println("├────┴───────┬────────────────────────────┤");
        System.out.printf("│ Échéance   │ %-26s │%n",
                tache.getEcheance() != null ? tache.getEcheance().toString() : "N/A");
        System.out.printf("│ Statut     │ %-26s │%n", tache.isStatut() ? "Done" : "In Progress");
        System.out.println("├────────────┴────────────────────────────┤");

        // Découper la description en lignes respectant la largeur du cadre
        String description = tache.getDescription();
        List<String> lignesDescription = decouperEnLignes(description, largeurDescription);
        System.out.println("│ Description                             │");
        for (String ligne : lignesDescription) {
            System.out.printf("│ %-39s │%n", ligne);
        }

        System.out.println("└─────────────────────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...", false);
    }

    /**
     * Découpe une chaîne en plusieurs lignes de longueur maximale.
     * 
     * @param texte   La chaîne à découper.
     * @param largeur La largeur maximale par ligne.
     * @return Une liste de lignes découpées.
     */
    private List<String> decouperEnLignes(String texte, int largeur) {
        List<String> lignes = new ArrayList<>();
        int index = 0;

        while (index < texte.length()) {
            int fin = Math.min(index + largeur, texte.length());
            lignes.add(texte.substring(index, fin));
            index = fin;
        }

        return lignes;
    }
}
