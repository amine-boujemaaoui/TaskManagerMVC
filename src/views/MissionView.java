package views;

import models.Mission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de l'affichage des missions et des interactions
 * utilisateur
 * pour les opérations liées aux missions.
 */
public class MissionView extends AbstractView {

    /**
     * Affiche le menu principal et capture le choix de l'utilisateur.
     *
     * @return Le choix de l'utilisateur sous forme de chaîne.
     */
    public String afficherMenuEtLireChoix() {
        clearConsole();
        String[] options = {
                "1. Ajouter une mission",
                "2. Afficher toutes les missions",
                "3. Afficher une mission",
                "0. Quitter"
        };

        afficherMenu("Commandes disponibles", options);

        return afficherEtLire("MissionManager");
    }

    /**
     * Capture les informations nécessaires pour créer une nouvelle mission.
     *
     * @param id L'identifiant unique de la mission.
     * @return Une nouvelle instance de {@link Mission}.
     */
    public Mission saisirMission(int id) {
        String titre = afficherEtLire("Titre:");
        LocalDate dateDebut = LocalDate.parse(afficherEtLire("Date de début (AAAA-MM-JJ):"));
        LocalDate dateFin = LocalDate.parse(afficherEtLire("Date de fin (AAAA-MM-JJ):"));
        String nomsInput = afficherEtLire("Noms associés (séparés par des points-virgules):");
        List<String> noms = List.of(nomsInput.split(";"));

        return new Mission(id, titre, dateDebut, dateFin, noms);
    }

    /**
     * Demande à l'utilisateur de saisir l'ID d'une mission pour une action donnée.
     *
     * @param action Une chaîne décrivant l'action (afficher les détails, etc.).
     * @return L'ID de la mission saisi par l'utilisateur.
     */
    public int demanderIdMission(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID de la mission à " + action + ":"));
    }

    /**
     * Affiche une liste de missions dans un tableau formaté.
     *
     * @param missions La liste des missions à afficher.
     */
    public void afficherMissions(List<Mission> missions) {
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
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...");
    }

    /**
     * Tronque une chaîne de texte si elle dépasse une certaine longueur et la
     * remplit si nécessaire.
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
     * Affiche les détails d'une mission avec un format clair.
     *
     * @param mission La mission dont les détails doivent être affichés.
     */
    public void afficherDetailsMission(Mission mission) {
        clearConsole();
        int largeurDescription = 37; // Largeur pour afficher les noms

        System.out.println("┌────┬────────────────────────────────────┐");
        System.out.printf("│ %-2d │ %-34s │%n", mission.getId(), mission.getTitre());
        System.out.println("├────┴───────┬────────────────────────────┤");
        System.out.printf("│ Début      │ %-26s │%n",
                mission.getDateDebut() != null ? mission.getDateDebut().toString() : "N/A");
        System.out.printf("│ Fin        │ %-26s │%n",
                mission.getDateFin() != null ? mission.getDateFin().toString() : "N/A");
        System.out.println("├────────────┴────────────────────────────┤");

        // Découper les noms en lignes respectant la largeur
        String noms = String.join(", ", mission.getNoms());
        List<String> lignesNoms = decouperEnLignes(noms, largeurDescription);
        System.out.println("│ Noms                                    │");
        for (String ligne : lignesNoms) {
            System.out.printf("│ %-39s │%n", ligne);
        }

        System.out.println("└─────────────────────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...");
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
