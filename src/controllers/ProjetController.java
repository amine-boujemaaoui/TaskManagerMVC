package controllers;

import models.Entities.Mission;
import models.Entities.Projet;
import models.Repositories.ProjetRepository;
import utils.AbstractFileUtil;
import utils.ProjetFileUtil;
import views.ProjetView;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur pour gérer les opérations sur les projets, y compris l'ajout,
 * l'affichage, la modification, la suppression et la sauvegarde des projets.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class ProjetController extends AbstractController<Projet> {

    /**
     * Contrôleur pour gérer les missions associées aux projets.
     */
    private final MissionController missionController;

    /**
     * Constructeur du contrôleur pour gérer les projets.
     * Initialise la vue et le référentiel pour les projets, ainsi que le contrôleur
     * des missions associées.
     * Charge les projets depuis un fichier à l'aide de l'utilitaire de fichier.
     *
     * @param view              La vue associée pour afficher et interagir avec les
     *                          projets.
     * @param repository        Le référentiel des projets.
     * @param missionController Le contrôleur pour gérer les missions associées aux
     *                          projets.
     */
    public ProjetController(ProjetView view, ProjetRepository repository, MissionController missionController) {
        super(view, repository);
        this.missionController = missionController;

        // Initialisation de l'utilitaire de fichier pour les projets
        this.fileUtil = AbstractFileUtil.getInstance(
                ProjetFileUtil.class,
                repository.getNomFichier(),
                missionController.getRepository());

        try {
            // Chargement des projets depuis le fichier
            List<Projet> projets = fileUtil.charger();
            repository.ajouterTout(projets);
        } catch (Exception e) {
            view.afficherMessage("Erreur lors du chargement des projets : " + e.getMessage());
        }
    }

    /**
     * Ajoute un nouveau projet.
     * Demande à l'utilisateur de sélectionner une mission et de fournir les
     * informations sur le projet.
     * Ajoute le projet avec la mission associée au référentiel.
     */
    @Override
    public void ajouter() {
        int id = repository.getTout().size() + 1;

        // Récupération des missions disponibles
        List<Mission> missionsDisponibles = missionController.getRepository().getTout();
        if (missionsDisponibles.isEmpty()) {
            view.afficherMessage("Aucune mission disponible. Impossible de créer un projet.");
            return;
        }

        // Sélection d'une mission
        Mission mission = ((ProjetView) view).choisirMission(missionsDisponibles);
        if (mission == null) {
            view.afficherMessage("Aucune mission sélectionnée. Annulation de la création du projet.");
            return;
        }

        // Saisie du projet
        Projet projet = view.ajouter(id);
        projet.setMission(mission);

        // Ajout du projet au référentiel
        repository.ajouter(projet);
        view.afficherMessage("Projet ajouté avec succès avec la mission associée !");
    }

    /**
     * Modifie un projet existant.
     * Demande à l'utilisateur de sélectionner un projet à modifier, et met à jour
     * ses informations, y compris la mission associée, si nécessaire.
     */
    @Override
    public void modifier() {
        int idProjet = view.demanderId("modifier");
        Optional<Projet> projetOpt = repository.getParId(idProjet);

        if (projetOpt.isEmpty()) {
            view.afficherMessage("Projet introuvable avec l'ID : " + idProjet);
            return;
        }

        Projet projet = projetOpt.get();
        Projet nouveauProjet = view.modifier(projet);

        if (nouveauProjet.getMission() == null) {
            List<Mission> missionsDisponibles = missionController.getRepository().getTout();
            Mission nouvelleMission = ((ProjetView) view).choisirMission(missionsDisponibles);
            nouveauProjet.setMission(nouvelleMission);
        }

        repository.modifier(nouveauProjet);
        view.afficherMessage("Projet modifié avec succès !");
    }

    /**
     * Supprime un projet existant.
     * Demande à l'utilisateur de sélectionner un projet à supprimer.
     * Supprime le projet du référentiel si celui-ci existe.
     */
    @Override
    public void supprimer() {
        int id = view.demanderId("supprimer");
        Optional<Projet> projetOpt = repository.getParId(id);

        if (projetOpt.isPresent()) {
            repository.supprimer(id);
            view.afficherMessage("Projet supprimé avec succès !");
        } else {
            view.afficherMessage("Projet introuvable avec l'ID : " + id);
        }
    }

    /**
     * Affiche tous les projets existants dans le référentiel.
     * Trie les projets par leur ID avant de les afficher.
     */
    @Override
    public void afficherTous() {
        List<Projet> projets = repository.getTout();
        projets.sort((p1, p2) -> Integer.compare(p1.getId(), p2.getId()));
        view.afficherTous(projets);
    }

    /**
     * Affiche les détails d'un projet spécifique.
     * Demande à l'utilisateur de fournir l'ID d'un projet, puis affiche ses
     * informations si celui-ci existe.
     */
    @Override
    public void afficherDetails() {
        int idProjet = view.demanderId("afficher les détails");
        Optional<Projet> projetOpt = repository.getParId(idProjet);

        if (projetOpt.isPresent()) {
            view.afficherDetails(projetOpt.get());
        } else {
            view.afficherMessage("Projet introuvable avec l'ID : " + idProjet);
        }
    }
}
