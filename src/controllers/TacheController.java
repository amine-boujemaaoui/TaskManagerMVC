package controllers;

import models.Entities.Tache;
import models.Repositories.TacheRepository;
import utils.AbstractFileUtil;
import utils.TacheFileUtil;
import views.TacheView;

import java.util.List;

/**
 * Contrôleur pour gérer les opérations sur les tâches, y compris l'ajout,
 * la modification, la suppression, l'affichage et la sauvegarde des tâches.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class TacheController extends AbstractController<Tache> {

    /**
     * Constructeur de la classe {@code TacheController}.
     * Initialise la vue et le référentiel des tâches, et charge les données des
     * tâches
     * depuis un fichier à l'aide d'un utilitaire de fichier.
     *
     * @param view       La vue pour afficher et interagir avec les tâches.
     * @param repository Le référentiel pour gérer les données des tâches.
     */
    public TacheController(TacheView view, TacheRepository repository) {
        super(view, repository);

        // Initialisation de l'utilitaire de fichier pour gérer les tâches
        this.fileUtil = AbstractFileUtil.getInstance(TacheFileUtil.class, repository.getNomFichier());

        try {
            // Chargement des tâches depuis le fichier
            List<Tache> taches = fileUtil.charger();
            repository.ajouterTout(taches); // Ajout des tâches au référentiel
        } catch (Exception e) {
            // Gestion des erreurs de chargement et affichage d'un message à l'utilisateur
            view.afficherMessage("Erreur lors du chargement des tâches : " + e.getMessage());
        }
    }

    /**
     * Associe une tâche à un projet.
     * Demande à l'utilisateur de sélectionner un projet existant et ajoute la tâche
     * à la liste des tâches du projet.
     * 
     * @param projetController Le contrôleur pour gérer les projets.
     */
    public void ajouterTacheAvecProjet(ProjetController projetController) {
        int idTache = repository.getTout().size() + 1;
        Tache tache = view.ajouter(idTache);

        int idProjet = view.demanderId("Associer la tâche à un projet");
        projetController.ajouterTacheAuProjet(idProjet, tache);

        repository.ajouter(tache);
        view.afficherMessage("Tâche créée et associée au projet avec succès !");
    }

}
