package controllers;

import models.Tache;
import models.TacheRepository;
import utils.AbstractFileUtil;
import utils.TacheFileUtil;
import views.TacheView;

import java.util.List;

/**
 * Contrôleur pour gérer les opérations sur les tâches, y compris l'ajout, la
 * modification,
 * la suppression, l'affichage et la sauvegarde des tâches.
 */
public class TacheController extends AbstractController<Tache> {

    /**
     * Constructeur de la classe {@code TacheController}.
     *
     * @param repository Le référentiel des tâches.
     * @param tacheView  La vue pour afficher et interagir avec les tâches.
     */
    public TacheController(TacheView view, TacheRepository repository) {
        super(view, repository);
        this.fileUtil = AbstractFileUtil.getInstance(TacheFileUtil.class, repository.getNomFichier());

        try {
            List<Tache> taches = fileUtil.charger();
            repository.ajouterTout(taches);
        } catch (Exception e) {
            view.afficherMessage("Erreur lors du chargement des tâches : " + e.getMessage());
        }
    }
}
