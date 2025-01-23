package controllers;

import models.Mission;
import models.MissionRepository;
import utils.MissionFileUtil;
import views.MissionView;

import java.util.List;

/**
 * Contrôleur pour gérer les opérations sur les missions, y compris l'ajout,
 * l'affichage et la sauvegarde des missions.
 */
public class MissionController extends AbstractController<Mission> {

    /**
     * Vue associée pour interagir avec l'utilisateur.
     */
    private final MissionView missionView;

    /**
     * Utilitaire pour la gestion de la persistance des missions dans un fichier
     * CSV.
     */
    private final MissionFileUtil missionFileUtil;

    /**
     * Constructeur de la classe {@code MissionController}.
     *
     * @param repository  Le référentiel des missions.
     * @param missionView La vue pour afficher et interagir avec les missions.
     */
    public MissionController(MissionRepository repository, MissionView missionView) {
        super(missionView, repository);
        this.missionView = missionView;
        this.missionFileUtil = new MissionFileUtil("resources/missions.csv");

        try {
            List<Mission> missions = missionFileUtil.charger();
            repository.ajouterToutesLesEntites(missions);
        } catch (Exception e) {
            missionView.afficherMessage("Erreur lors du chargement des missions : " + e.getMessage());
        }
    }

    /**
     * Exécute le contrôleur principal, permettant à l'utilisateur de naviguer entre
     * différentes options pour gérer les missions.
     */
    @Override
    public void executer() {
        boolean running = true;

        while (running) {
            String choix = missionView.afficherMenuEtLireChoix();

            switch (choix) {
                case "1" -> ajouterMission();
                case "2" -> afficherMissions();
                case "3" -> afficherDetailsMission();
                case "0" -> running = false;
                default -> missionView.afficherMessage("Option invalide. Veuillez réessayer.");
            }
        }

        try {
            missionFileUtil.sauvegarder(repository.getToutesLesEntites());
            missionView.afficherMessage("Missions sauvegardées avec succès !");
        } catch (Exception e) {
            missionView.afficherMessage("Erreur lors de la sauvegarde des missions : " + e.getMessage());
        }
    }

    /**
     * Ajoute une nouvelle mission au référentiel.
     */
    private void ajouterMission() {
        int id = repository.getToutesLesEntites().size() + 1;
        Mission nouvelleMission = missionView.saisirMission(id);
        repository.ajouterEntite(nouvelleMission);
        missionView.afficherMessage("Mission ajoutée avec succès !");
    }

    /**
     * Affiche la liste de toutes les missions.
     */
    private void afficherMissions() {
        List<Mission> missions = repository.getToutesLesEntites();
        missions.sort((m1, m2) -> Integer.compare(m2.getId(), m1.getId()));
        missionView.afficherMissions(missions);
    }

    /**
     * Affiche les détails d'une mission spécifique en fonction de l'identifiant
     * saisi
     * par l'utilisateur.
     */
    private void afficherDetailsMission() {
        int id = missionView.demanderIdMission("afficher les détails");
        repository.getEntiteParId(id).ifPresentOrElse(
                missionView::afficherDetailsMission,
                () -> missionView.afficherMessage("Mission introuvable."));
    }
}
