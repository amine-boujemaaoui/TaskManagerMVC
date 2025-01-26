package controllers;

import models.Entities.AbstractEntity;
import models.Repositories.AbstractRepository;
import views.AbstractView;
import utils.AbstractFileUtil;

import java.util.Optional;

/**
 * Contrôleur abstrait générique fournissant les opérations de base pour gérer
 * des entités.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 *
 * @param <T> Le type d'entité géré par ce contrôleur, qui doit étendre
 *            {@link AbstractEntity}.
 */
public abstract class AbstractController<T extends AbstractEntity> {

    /**
     * Vue associée pour interagir avec l'utilisateur.
     */
    protected AbstractView<T> view;

    /**
     * Référentiel contenant les données des entités.
     */
    protected AbstractRepository<T> repository;

    /**
     * Utilitaire pour la gestion de la persistance des entités.
     */
    protected AbstractFileUtil<T> fileUtil;

    /**
     * Constructeur pour initialiser le contrôleur avec la vue et le référentiel.
     *
     * @param view       La vue associée pour afficher et interagir avec les
     *                   entités.
     * @param repository Le référentiel pour gérer les données des entités.
     */
    public AbstractController(AbstractView<T> view, AbstractRepository<T> repository) {
        this.view = view;
        this.repository = repository;
    }

    /**
     * Ajoute une nouvelle entité en demandant les informations via la vue.
     * L'entité est ensuite ajoutée au référentiel.
     */
    public void ajouter() {
        int id = repository.getTout().size() + 1;
        T entity = view.ajouter(id);
        repository.ajouter(entity);
        view.afficherMessage("Ajoutée avec succès !");
    }

    /**
     * Modifie une entité existante.
     * Si l'entité avec l'ID donné existe, ses nouvelles informations sont demandées
     * via la vue, puis mises à jour dans le référentiel.
     */
    public void modifier() {
        int id = view.demanderId("modifier");
        Optional<T> entiteExistante = repository.getParId(id);

        if (entiteExistante.isPresent()) {
            T entiteModifiee = view.modifier(entiteExistante.get());
            repository.modifier(entiteModifiee);
            view.afficherMessage("Entité modifiée avec succès !");
        } else {
            view.afficherMessage("Entité non trouvée avec l'ID : " + id);
        }
    }

    /**
     * Supprime une entité existante.
     * Si l'entité avec l'ID donné existe, elle est supprimée du référentiel.
     */
    public void supprimer() {
        int id = view.demanderId("supprimer");
        Optional<T> entiteExistante = repository.getParId(id);

        if (entiteExistante.isPresent()) {
            repository.supprimer(id);
            view.afficherMessage("Entité supprimée avec succès !");
        } else {
            view.afficherMessage("Entité non trouvée avec l'ID : " + id);
        }
    }

    /**
     * Affiche toutes les entités présentes dans le référentiel.
     * Les entités sont récupérées et affichées via la vue.
     */
    public void afficherTous() {
        var toutesLesEntites = repository.getTout();
        view.afficherTous(toutesLesEntites);
    }

    /**
     * Affiche les détails d'une entité spécifique en fonction de son ID.
     * Si l'entité avec l'ID donné existe, ses détails sont affichés via la vue.
     */
    public void afficherDetails() {
        int id = view.demanderId("");
        Optional<T> entite = repository.getParId(id);

        if (entite.isPresent()) {
            view.afficherDetails(entite.get());
        } else {
            view.afficherMessage("Entité non trouvée avec l'ID : " + id);
        }
    }

    /**
     * Récupère le référentiel associé au contrôleur.
     *
     * @return Le référentiel des entités.
     */
    public AbstractRepository<T> getRepository() {
        return repository;
    }

    /**
     * Exécute la boucle principale du contrôleur.
     * Affiche le menu et gère les options choisies par l'utilisateur.
     * À la fin, les données sont sauvegardées via l'utilitaire de fichier.
     */
    public void executer() {
        boolean running = true;

        while (running) {
            String choix = view.afficherMenuEtLireChoix();

            switch (choix) {
                case "1" -> ajouter();
                case "2" -> modifier();
                case "3" -> supprimer();
                case "4" -> afficherTous();
                case "5" -> afficherDetails();
                case "0" -> running = false;
                default -> view.afficherMessage("Option invalide. Veuillez réessayer.");
            }
        }

        try {
            fileUtil.sauvegarder(repository.getTout());
            view.afficherMessage("Données sauvegardées avec succès !");
        } catch (Exception e) {
            view.afficherMessage("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}
