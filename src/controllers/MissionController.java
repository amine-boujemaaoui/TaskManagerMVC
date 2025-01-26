package controllers;

import models.Mission;
import models.MissionRepository;
import utils.AbstractFileUtil;
import utils.MissionFileUtil;
import views.MissionView;

import java.util.List;

/**
 * Contrôleur pour gérer les opérations sur les missions, y compris l'ajout,
 * l'affichage, la modification, la suppression et la sauvegarde des missions.
 */
public class MissionController extends AbstractController<Mission> {

    /**
     * Constructeur de la classe {@code MissionController}.
     *
     * @param repository  Le référentiel des missions.
     * @param missionView La vue pour afficher et interagir avec les missions.
     */
    public MissionController(MissionView view, MissionRepository repository) {
        super(view, repository);

        this.fileUtil = AbstractFileUtil.getInstance(MissionFileUtil.class, repository.getNomFichier());

        try {
            List<Mission> missions = fileUtil.charger();
            repository.ajouterTout(missions);
        } catch (Exception e) {
            view.afficherMessage("Erreur lors du chargement des missions : " + e.getMessage());
        }
    }
}
