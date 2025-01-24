package controllers;

import models.Mission;
import models.MissionRepository;
import models.Projet;
import models.ProjetRepository;
import utils.ProjetFileUtil;
import views.ProjetView;

import java.util.List;

public class ProjetController extends AbstractController<Projet> {

    private final ProjetView projetView;
    private final MissionController missionController;
    private final ProjetFileUtil projetFileUtil;

    public ProjetController(ProjetRepository repository, ProjetView projetView, MissionController missionController) {
        super(projetView, repository);
        this.projetView = projetView;
        this.missionController = missionController;
        this.projetFileUtil = new ProjetFileUtil("resources/projets.csv", (MissionRepository) missionController.getRepository());

        try {
            List<Projet> projets = projetFileUtil.charger();
            repository.ajouterToutesLesEntites(projets);
        } catch (Exception e) {
            projetView.afficherMessage("Erreur lors du chargement des projets : " + e.getMessage());
        }
    }

    @Override
    public void executer() {
        boolean running = true;

        while (running) {
            String choix = projetView.afficherMenuEtLireChoix();

            switch (choix) {
                case "1" -> ajouterProjet();
                case "2" -> afficherProjets();
                case "3" -> afficherDetailsProjet();
                case "0" -> running = false;
                default -> projetView.afficherMessage("Option invalide. Veuillez réessayer.");
            }
        }

        try {
            projetFileUtil.sauvegarder(repository.getToutesLesEntites());
            projetView.afficherMessage("Projets sauvegardés avec succès !");
        } catch (Exception e) {
            projetView.afficherMessage("Erreur lors de la sauvegarde des projets : " + e.getMessage());
        }
    }

    private void ajouterProjet() {
        int id = repository.getToutesLesEntites().size() + 1;

        // Afficher les missions disponibles via le MissionController
        List<Mission> missionsDisponibles = missionController.getRepository().getToutesLesEntites();
        if (missionsDisponibles.isEmpty()) {
            projetView.afficherMessage("Aucune mission disponible. Impossible de créer un projet.");
            return;
        }

        // Permettre à l'utilisateur de sélectionner une mission
        Mission mission = projetView.choisirMission(missionsDisponibles);
        if (mission == null) {
            projetView.afficherMessage("Aucune mission sélectionnée. Annulation de la création du projet.");
            return;
        }

        // Capturer les détails du projet
        Projet projet = projetView.saisirProjet(id);
        projet.setMission(mission);
        repository.ajouterEntite(projet);

        projetView.afficherMessage("Projet ajouté avec succès, avec la mission associée !");
    }

    private void afficherProjets() {
        List<Projet> projets = repository.getToutesLesEntites();
        projetView.afficherProjets(projets);
    }

    private void afficherDetailsProjet() {
        int idProjet = projetView.demanderIdProjet("afficher les détails");
        Projet projet = repository.getEntiteParId(idProjet).orElse(null);

        if (projet == null) {
            projetView.afficherMessage("Projet introuvable.");
            return;
        }

        projetView.afficherDetailsProjet(projet);
    }
}
