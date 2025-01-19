package controllers;

import java.time.LocalDate;

import models.Tache;
import models.TacheRepository;
import views.MainView;

/**
 * Contrôleur pour gérer les interactions entre le modèle TacheRepository
 * et les vues liées aux tâches.
 */
public class TacheController extends AbstractController {

    private final TacheRepository tacheRepository;
    private final MainView mainView;

    /**
     * Constructeur pour initialiser le contrôleur avec le modèle et la vue.
     *
     * @param tacheRepository le référentiel de tâches utilisé par le contrôleur.
     * @param mainView        la vue principale associée à ce contrôleur.
     */
    public TacheController(TacheRepository tacheRepository, MainView mainView) {
        this.tacheRepository = tacheRepository;
        this.mainView = mainView;
    }

    /**
     * Implémente la méthode héritée pour gérer les actions utilisateur spécifiques.
     *
     * @param action une chaîne représentant l'action utilisateur.
     */
    @Override
    public void handleRequest(String action) {
        switch (action) {
            case "ajouter":
                ajouterTache();
                break;
            case "supprimer":
                supprimerTache();
                break;
            case "modifier":
                modifierTache(); // Nouvelle méthode
                break;
            case "afficher":
                updateView();
                break;
            default:
                System.out.println("Action non reconnue : " + action);
        }
    }

    /**
     * Démarre la boucle principale pour gérer les interactions utilisateur.
     */
    public void start() {
        boolean running = true;
        while (running) {
            updateView(); // Affiche la vue principale
            String input = getUserInput(); // Capture l'entrée utilisateur

            switch (input) {
                case "1":
                    handleRequest("ajouter");
                    break;
                case "2":
                    handleRequest("supprimer");
                    break;
                case "3":
                    handleRequest("modifier"); // Nouvelle commande
                    break;
                case "4":
                    running = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Commande non reconnue. Essayez encore.");
            }
        }
    }

    /**
     * Met à jour la vue principale en affichant l'état actuel des tâches.
     */
    @Override
    public void updateView() {
        mainView.display();
    }

    /**
     * Capture l'entrée utilisateur via la vue principale.
     *
     * @return l'entrée utilisateur sous forme de chaîne.
     */
    public String getUserInput() {
        return mainView.getInput();
    }

    /**
     * Ajoute une nouvelle tâche au référentiel.
     */
    public void ajouterTache() {
        String titre = mainView.getTitreTache(); // Demande à la vue d'obtenir le titre
        String description = mainView.getDescriptionTache(); // Demande à la vue d'obtenir la description

        Tache tache = new Tache(titre, description);
        tacheRepository.ajouterTache(tache); // Ajoute la tâche au modèle
        mainView.afficherMessage("Tâche ajoutée !"); // Notifie l'utilisateur via la vue
    }

    /**
     * Supprime une tâche existante en fonction de son index.
     */
    public void supprimerTache() {
        int index = mainView.getIndexTache(); // Demande à la vue de récupérer l'index

        if (index >= 0 && index < tacheRepository.getToutesLesTaches().size()) {
            tacheRepository.supprimerTache(tacheRepository.getToutesLesTaches().get(index)); // Supprime la tâche
            mainView.afficherMessage("Tâche supprimée !"); // Notifie l'utilisateur via la vue
        } else {
            mainView.afficherMessage("Index invalide."); // Notifie l'utilisateur en cas d'erreur
        }
    }

    public void modifierTache() {
        try {
            // Récupération des données de la vue
            int index = mainView.getIndexTache(); // Récupère l'index
            String nouveauTitre = mainView.getTitreTache(); // Récupère le nouveau titre
            String nouvelleDescription = mainView.getDescriptionTache(); // Récupère la nouvelle description
            LocalDate nouvelleDateEcheance = mainView.getEcheance(); // Récupère la nouvelle date d'échéance
            boolean nouveauStatut = mainView.getStatut(); // Récupère le statut choisi par l'utilisateur

            // Modification dans le modèle
            tacheRepository.modifierTache(index, nouveauTitre, nouvelleDescription, nouvelleDateEcheance,
                    nouveauStatut);

            mainView.afficherMessage("Tâche modifiée avec succès !");
        } catch (IllegalArgumentException e) {
            mainView.afficherMessage("Erreur : " + e.getMessage());
        }
    }


}
