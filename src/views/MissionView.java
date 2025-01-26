package views;

import models.Mission;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe responsable de l'affichage et des interactions utilisateur pour les
 * missions.
 */
public class MissionView extends AbstractView<Mission> {

    @Override
    public Mission ajouter(int id) {
        String titre = afficherEtLire("Entrez le titre de la mission :");
        LocalDate dateDebut = LocalDate.parse(afficherEtLire("Entrez la date de début (AAAA-MM-JJ) :"));
        LocalDate dateFin = LocalDate.parse(afficherEtLire("Entrez la date de fin (AAAA-MM-JJ) :"));
        String nomsInput = afficherEtLire("Entrez les noms associés (séparés par des points-virgules) :");
        List<String> noms = List.of(nomsInput.split(";"));

        return new Mission(id, titre, dateDebut, dateFin, noms);
    }

    @Override
    public Mission modifier(Mission mission) {
        String nouveauTitre = afficherEtLire("Entrez le nouveau titre (actuel : " + mission.getTitre() + ") :");
        if (nouveauTitre.isBlank()) {
            nouveauTitre = mission.getTitre();
        }

        String nouvelleDateDebutInput = afficherEtLire(
                "Entrez la nouvelle date de début (actuelle : " + mission.getDateDebut() + ") :");
        LocalDate nouvelleDateDebut = nouvelleDateDebutInput.isBlank()
                ? mission.getDateDebut()
                : LocalDate.parse(nouvelleDateDebutInput);

        String nouvelleDateFinInput = afficherEtLire(
                "Entrez la nouvelle date de fin (actuelle : " + mission.getDateFin() + ") :");
        LocalDate nouvelleDateFin = nouvelleDateFinInput.isBlank()
                ? mission.getDateFin()
                : LocalDate.parse(nouvelleDateFinInput);

        String nomsInput = afficherEtLire(
                "Entrez les nouveaux noms associés (actuels : " + String.join(";", mission.getNoms()) + ") :");
        List<String> nouveauxNoms = nomsInput.isBlank()
                ? mission.getNoms()
                : List.of(nomsInput.split(";"));

        return new Mission(mission.getId(), nouveauTitre, nouvelleDateDebut, nouvelleDateFin, nouveauxNoms);
    }

    @Override
    public void supprimer() {
        int id = demanderId("supprimer");
        afficherMessage("La mission avec l'ID " + id + " sera supprimée.");
    }

    @Override
    public void afficherTous(List<Mission> missions) {
        clearConsole();
        String ligneFormat = "│ %-4s │ %-20s │ %-12s │ %-12s │ %-23s │%n";

        System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-83s │%n", "Liste des missions (" + missions.size() + ")");
        System.out.println("├──────┬──────────────────────┬──────────────┬──────────────┬─────────────────────────┤");
        System.out.printf(ligneFormat, "ID", "Titre", "Début", "Fin", "Noms associés");
        System.out.println("├──────┼──────────────────────┼──────────────┼──────────────┼─────────────────────────┤");

        if (missions.isEmpty()) {
            System.out.printf(ligneFormat, "", "", "", "", "");
        } else {
            for (Mission mission : missions) {
                String id = String.valueOf(mission.getId());
                String titre = tronquer(mission.getTitre(), 20);
                String debut = mission.getDateDebut() != null ? mission.getDateDebut().toString() : "N/A";
                String fin = mission.getDateFin() != null ? mission.getDateFin().toString() : "N/A";
                String noms = tronquer(String.join(";", mission.getNoms()), 23);

                System.out.printf(ligneFormat, id, titre, debut, fin, noms);
            }
        }

        System.out.println("└──────┴──────────────────────┴──────────────┴──────────────┴─────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour continuer...");
    }

    @Override
    public void afficherDetails(Mission mission) {
        clearConsole();
        int largeurNom = 37;

        System.out.println("┌────┬────────────────────────────────────┐");
        System.out.printf("│ %-2d │ %-34s │%n", mission.getId(), mission.getTitre());
        System.out.println("├────┴───────┬────────────────────────────┤");
        System.out.printf("│ Début      │ %-26s │%n",
                mission.getDateDebut() != null ? mission.getDateDebut().toString() : "N/A");
        System.out.printf("│ Fin        │ %-26s │%n",
                mission.getDateFin() != null ? mission.getDateFin().toString() : "N/A");
        System.out.println("├────────────┴────────────────────────────┤");

        // Découper les noms associés en plusieurs lignes si nécessaire
        String noms = String.join(", ", mission.getNoms());
        List<String> lignesNoms = decouperEnLignes(noms, largeurNom);
        System.out.println("│ Noms associés                           │");
        for (String ligne : lignesNoms) {
            System.out.printf("│ %-39s │%n", ligne);
        }

        System.out.println("└─────────────────────────────────────────┘");
        afficherEtLire("Appuyez sur Entrée pour revenir au menu...");
    }

    @Override
    public String afficherMenuEtLireChoix() {
        clearConsole();
        String[] options = {
                "1. Ajouter une mission",
                "2. Modifier une mission",
                "3. Supprimer une mission",
                "4. Afficher toutes les missions",
                "5. Afficher les détails d'une mission",
                "0. Quitter"
        };

        afficherMenu("Menu des missions", options);
        return afficherEtLire("Choisissez une option :");
    }

    @Override
    public void actualiser() {
    }


}
