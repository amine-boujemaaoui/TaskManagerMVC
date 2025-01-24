package views;

import models.Mission;
import models.Projet;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable des interactions utilisateur pour les projets.
 */
public class ProjetView extends AbstractView {

    public String afficherMenuEtLireChoix() {
        clearConsole();
        String[] options = {
                "1. Ajouter un projet",
                "2. Afficher tous les projets",
                "3. Afficher les détails d'un projet",
                "0. Quitter"
        };

        afficherMenu("Menu Projet", options);

        return afficherEtLire("ProjetManager");
    }

    /**
     * Permet à l'utilisateur de choisir une mission parmi une liste.
     *
     * @param missions La liste des missions disponibles.
     * @return La mission sélectionnée ou null si l'utilisateur annule.
     */
    public Mission choisirMission(List<Mission> missions) {
        clearConsole();

        String ligneFormat = "│ %-2s │ %-20s │ %-10s │ %-10s │ %-29s │%n";

        System.out.println(
                "┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-83s │%n", " Liste des missions (" + missions.size() + ")");
        System.out.println("├────┬──────────────────────┬────────────┬────────────┬───────────────────────────────┤");
        System.out.printf(ligneFormat, "ID", "Titre", "Début", "Fin", "Noms");
        System.out.println("├────┼──────────────────────┼────────────┼────────────┼───────────────────────────────┤");

        if (missions.isEmpty()) {
            System.out.printf(ligneFormat, "", "", "", "", "");
        } else {
            for (Mission mission : missions) {
                String id = String.format("%2d", mission.getId());
                String titreMission = tronquer(mission.getTitre(), 20);
                String dateDebut = mission.getDateDebut() != null ? mission.getDateDebut().toString() : "N/A";
                String dateFin = mission.getDateFin() != null ? mission.getDateFin().toString() : "N/A";
                String noms = tronquer(String.join(";", mission.getNoms()), 29);
                System.out.printf(ligneFormat, id, titreMission, dateDebut, dateFin, noms);
            }
        }

        System.out.println("└────┴──────────────────────┴────────────┴────────────┴───────────────────────────────┘");

        System.out.println("Veuillez sélectionner une mission pour le projet (0. Annuler)");

        int choix = Integer.parseInt(afficherEtLire("Votre choix :"));
        if (choix == 0) {
            return null; // Annulation
        }

        if (choix > 0 && choix <= missions.size()) {
            return missions.get(choix - 1);
        } else {
            afficherMessage("Choix invalide.");
            return null;
        }
    }

    /**
     * Capture les informations nécessaires pour créer un nouveau projet.
     *
     * @param id L'identifiant unique du projet.
     * @return Une nouvelle instance de {@link Projet}.
     */
    public Projet saisirProjet(int id) {
        String nom = afficherEtLire("Nom du projet :");
        String description = afficherEtLire("Description du projet :");
        return new Projet(id, nom, description, null, null); // La mission sera définie après
    }

    /**
     * Affiche une liste de projets dans un tableau formaté.
     *
     * @param projets La liste des projets à afficher.
     */
    public void afficherProjets(List<Projet> projets) {
        clearConsole();
        String ligneFormat = "│ %-2s │ %-20s │ %-10s │ %-29s │%n";

        System.out.println(
                "┌───────────────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-73s │%n", " Liste des projets (" + projets.size() + ")");
        System.out.println("├────┬──────────────────────┬────────────┬───────────────────────────────┤");
        System.out.printf(ligneFormat, "ID", "Nom", "Mission", "Description");
        System.out.println("├────┼──────────────────────┼────────────┼───────────────────────────────┤");

        if (projets.isEmpty()) {
            System.out.printf(ligneFormat, "", "", "", "");
        } else {
            for (Projet projet : projets) {
                String id = String.format("%2d", projet.getId());
                String nom = tronquer(projet.getNom(), 20);
                String mission = projet.getMission() != null ? projet.getMission().getTitre() : "Aucune";
                String description = tronquer(projet.getDescription(), 29);

                System.out.printf(ligneFormat, id, nom, mission, description);
            }
        }

        System.out.println("└────┴──────────────────────┴────────────┴───────────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...", false);
    }

    /**
     * Tronque une chaîne de texte si elle dépasse une certaine longueur.
     *
     * @param texte   Le texte à tronquer.
     * @param largeur La largeur maximale.
     * @return La chaîne tronquée.
     */
    private String tronquer(String texte, int largeur) {
        if (texte.length() > largeur) {
            return texte.substring(0, largeur - 3) + "...";
        }
        return texte;
    }

    /**
     * Permet à l'utilisateur de saisir l'ID d'un projet pour une action donnée.
     *
     * @param action Description de l'action pour laquelle l'ID est demandé.
     * @return L'ID saisi par l'utilisateur.
     */
    public int demanderIdProjet(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID du projet pour " + action + " :"));
    }

    /**
     * Affiche les détails d'un projet avec un format clair.
     *
     * @param projet Le projet dont les détails doivent être affichés.
     */
    public void afficherDetailsProjet(Projet projet) {
        clearConsole();
        int largeurDescription = 37; // Largeur pour afficher la description

        System.out.println("┌────┬────────────────────────────────────┐");
        System.out.printf("│ %-2d │ %-34s │%n", projet.getId(), projet.getNom());
        System.out.println("├────┴───────┬────────────────────────────┤");

        System.out.printf("│ Mission ID │ %-26s │%n",
                projet.getMission() != null ? projet.getMission().getId() : "N/A");
        System.out.printf("│ Mission    │ %-26s │%n",
                projet.getMission() != null ? projet.getMission().getTitre() : "N/A");

        System.out.println("├────────────┴────────────────────────────┤");

        // Découper la description en lignes respectant la largeur
        String description = projet.getDescription();
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
