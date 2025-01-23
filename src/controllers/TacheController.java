package controllers;

import models.Tache;
import models.TacheRepository;
import utils.TacheFileUtil;
import views.TacheView;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur pour gérer les opérations sur les tâches, y compris l'ajout, la
 * modification,
 * la suppression, l'affichage et la sauvegarde des tâches.
 */
public class TacheController extends AbstractController<Tache> {

    /**
     * Vue associée pour interagir avec l'utilisateur.
     */
    private final TacheView tacheView;

    /**
     * Utilitaire pour la gestion de la persistance des tâches dans un fichier CSV.
     */
    private final TacheFileUtil tacheFileUtil;

    /**
     * Constructeur de la classe {@code TacheController}.
     *
     * @param repository Le référentiel des tâches.
     * @param tacheView  La vue pour afficher et interagir avec les tâches.
     */
    public TacheController(TacheRepository repository, TacheView tacheView) {
        super(tacheView, repository);
        this.tacheView = tacheView;
        this.tacheFileUtil = new TacheFileUtil("resources/taches.csv");

        try {
            List<Tache> taches = tacheFileUtil.charger();
            repository.ajouterToutesLesEntites(taches);
        } catch (Exception e) {
            tacheView.afficherMessage("Erreur lors du chargement des tâches : " + e.getMessage());
        }
    }

    /**
     * Exécute le contrôleur principal, permettant à l'utilisateur de naviguer entre
     * différentes options pour gérer les tâches.
     */
    @Override
    public void executer() {
        boolean running = true;

        while (running) {
            String choix = tacheView.afficherMenuEtLireChoix();

            switch (choix) {
                case "1" -> ajouterTache();
                case "2" -> modifierTache();
                case "3" -> supprimerTache();
                case "4" -> afficherTaches();
                case "5" -> afficherDetailsTache();
                case "0" -> running = false;
                default -> tacheView.afficherMessage("Option invalide. Veuillez réessayer.");
            }
        }

        try {
            tacheFileUtil.sauvegarder(repository.getToutesLesEntites());
            tacheView.afficherMessage("Tâches sauvegardées avec succès !");
        } catch (Exception e) {
            tacheView.afficherMessage("Erreur lors de la sauvegarde des tâches : " + e.getMessage());
        }
    }

    /**
     * Ajoute une nouvelle tâche au référentiel.
     */
    private void ajouterTache() {
        int id = repository.getToutesLesEntites().size() + 1;
        Tache nouvelleTache = tacheView.saisirTache(id);
        repository.ajouterEntite(nouvelleTache);
        tacheView.afficherMessage("Tâche ajoutée avec succès !");
    }

    /**
     * Modifie une tâche existante en fonction de l'identifiant saisi par
     * l'utilisateur.
     */
    private void modifierTache() {
        int id = tacheView.demanderIdTache("modifier");
        Optional<Tache> tacheOpt = repository.getEntiteParId(id);

        tacheOpt.ifPresentOrElse(tache -> {
            Tache modifications = tacheView.modifierTacheSansValidation(tache);

            if (!modifications.getTitre().isBlank()) {
                tache.setTitre(modifications.getTitre());
            }
            if (!modifications.getDescription().isBlank()) {
                tache.setDescription(modifications.getDescription());
            }
            if (modifications.getEcheance() != null) {
                tache.setEcheance(modifications.getEcheance());
            }
            tache.setStatut(modifications.isStatut());

            repository.modifierEntite(tache);

            tacheView.afficherMessage("Tâche modifiée avec succès !");
        }, () -> tacheView.afficherMessage("Tâche introuvable."));
    }

    /**
     * Supprime une tâche existante en fonction de l'identifiant saisi par
     * l'utilisateur.
     */
    private void supprimerTache() {
        int id = tacheView.demanderIdTache("supprimer");
        if (repository.getEntiteParId(id).isPresent()) {
            repository.supprimerEntite(id);
            tacheView.afficherMessage("Tâche supprimée avec succès !");
        } else {
            tacheView.afficherMessage("Tâche introuvable.");
        }
    }

    /**
     * Affiche la liste de toutes les tâches.
     */
    private void afficherTaches() {
        List<Tache> taches = repository.getToutesLesEntites();
        taches.sort((t1, t2) -> Integer.compare(t2.getId(), t1.getId()));
        tacheView.afficherTaches(taches);
    }

    /**
     * Affiche les détails d'une tâche spécifique en fonction de l'identifiant saisi
     * par l'utilisateur.
     */
    private void afficherDetailsTache() {
        int id = tacheView.demanderIdTache("afficher les détails");
        Optional<Tache> tacheOpt = repository.getEntiteParId(id);

        tacheOpt.ifPresentOrElse(
                tacheView::afficherDetailsTache,
                () -> tacheView.afficherMessage("Tâche introuvable."));
    }

}
