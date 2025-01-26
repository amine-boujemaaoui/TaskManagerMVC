package controllers;

import models.Entities.Mission;
import models.Repositories.MissionRepository;
import utils.AbstractFileUtil;
import utils.MissionFileUtil;
import views.MissionView;

import java.util.List;

/**
 * Contrôleur pour gérer les opérations sur les missions, y compris l'ajout,
 * l'affichage, la modification, la suppression et la sauvegarde des missions.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class MissionController extends AbstractController<Mission> {

    /**
     * Constructeur de la classe {@code MissionController}.
     * Initialise le contrôleur avec une vue et un référentiel pour les missions,
     * et charge les données des missions depuis un fichier à l'aide d'un utilitaire
     * spécifique.
     *
     * @param view       La vue pour afficher et interagir avec les missions.
     * @param repository Le référentiel pour gérer les données des missions.
     */
    public MissionController(MissionView view, MissionRepository repository) {
        super(view, repository);

        // Initialisation de l'utilitaire de fichier pour la persistance des missions
        this.fileUtil = AbstractFileUtil.getInstance(MissionFileUtil.class, repository.getNomFichier());

        try {
            // Chargement des missions depuis le fichier
            List<Mission> missions = fileUtil.charger();
            repository.ajouterTout(missions);
        } catch (Exception e) {
            // Gestion des erreurs de chargement et affichage d'un message à l'utilisateur
            view.afficherMessage("Erreur lors du chargement des missions : " + e.getMessage());
        }
    }
}
