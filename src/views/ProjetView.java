package views;

import models.Projet;
import models.Mission;

import java.util.List;

/**
 * Classe responsable des interactions utilisateur pour les projets.
 */
public class ProjetView extends AbstractView<Projet> {

    @Override
    public Projet ajouter(int id) {
        String nom = afficherEtLire("Nom du projet :");
        String description = afficherEtLire("Description du projet :");
        return new Projet(id, nom, description, null, null); // La mission sera définie après
    }

    @Override
    public Projet modifier(Projet projet) {
        String nouveauNom = afficherEtLire("Entrez le nouveau nom (actuel : " + projet.getNom() + ") :");
        String nouvelleDescription = afficherEtLire(
                "Entrez la nouvelle description (actuelle : " + projet.getDescription() + ") :");
        Mission nouvelleMission = null;
        projet.setNom(nouveauNom);
        projet.setDescription(nouvelleDescription);
        projet.setMission(nouvelleMission);
        return projet;
    }

    @Override
    public void supprimer() {
        int id = demanderId("supprimer");
        afficherMessage("Le projet avec l'ID " + id + " sera supprimé.");
    }

    @Override
    public void afficherTous(List<Projet> projets) {
        clearConsole();
        String ligneFormat = "│ %-2s │ %-20s │ %-3s │ %-29s │ %-29s │%n";

        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-95s │%n", " Liste des projets (" + projets.size() + ")");
        System.out.println("├────┬──────────────────────┬─────┬───────────────────────────────┬───────────────────────────────┤");
        System.out.printf(ligneFormat, "ID", "Nom", "ID", "Mission", "Description");
        System.out.println("├────┼──────────────────────┼─────┼───────────────────────────────┼───────────────────────────────┤");

        if (projets.isEmpty()) {
            System.out.printf(ligneFormat, "", "", "", "", "");
        } else {
            for (Projet projet : projets) {
                String id = String.format("%2d", projet.getId());
                String nom = tronquer(projet.getNom(), 20);
                String idMission = projet.getMission() != null ? String.valueOf(projet.getMission().getId()) : "N/A";
                String mission = tronquer(projet.getMission() != null ? projet.getMission().getTitre() : "Aucune", 29);
                String description = tronquer(projet.getDescription(), 29);

                System.out.printf(ligneFormat, id, nom, idMission, mission, description);
            }
        }

        System.out.println("└────┴──────────────────────┴─────┴───────────────────────────────┴───────────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...");
    }

    @Override
    public void afficherDetails(Projet projet) {
        clearConsole();
        int largeurDescription = 37;

        System.out.println("┌────┬────────────────────────────────────┐");
        System.out.printf("│ %-2d │ %-34s │%n", projet.getId(), projet.getNom());
        System.out.println("├────┴───────┬────────────────────────────┤");

        System.out.printf("│ Mission ID │ %-26s │%n",
                projet.getMission() != null ? projet.getMission().getId() : "N/A");
        System.out.printf("│ Mission    │ %-26s │%n",
                projet.getMission() != null ? projet.getMission().getTitre() : "N/A");

        System.out.println("├────────────┴────────────────────────────┤");

        String description = projet.getDescription();
        List<String> lignesDescription = decouperEnLignes(description, largeurDescription);
        System.out.println("│ Description                             │");
        for (String ligne : lignesDescription) {
            System.out.printf("│ %-39s │%n", ligne);
        }

        System.out.println("└─────────────────────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...");
    }

    @Override
    public String afficherMenuEtLireChoix() {
        clearConsole();
        String[] options = {
                "1. Ajouter un projet",
                "2. Modifier un projet",
                "3. Supprimer un projet",
                "4. Afficher tous les projets",
                "5. Afficher les détails d'un projet",
                "0. Quitter"
        };

        afficherMenu("Menu Projet", options);
        return afficherEtLire("Choisissez une option :");
    }

    @Override
    public int demanderId(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID du projet pour " + action + " :"));
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
            return null;
        }

        if (choix > 0 && choix <= missions.size()) {
            return missions.get(choix - 1);
        } else {
            afficherMessage("Choix invalide.");
            return null;
        }
    }

    @Override
    public void actualiser() {
    }
}
