package controllers;

import java.time.LocalDate;
import java.util.Optional;

import models.Tache;
import models.TacheRepository;
import views.MainView;

/**
 * Contrôleur pour gérer les interactions entre le modèle TacheRepository
 * et les vues liées aux tâches.
 */
public class TacheController {

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
     * Démarre la boucle principale pour gérer les interactions utilisateur.
     */
    public void start() {
        boolean running = true;
        while (running) {
            updateView(); // Affiche la vue principale
            String input = getUserInput(); // Capture l'entrée utilisateur

            switch (input) {
                case "1":
                    ajouterTache();
                    break;
                case "2":
                    supprimerTache();
                    break;
                case "3":
                    modifierTache(); // Nouvelle commande
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
        int id = mainView.getIdTache(); // Récupère l'ID de la tâche
        String titre = mainView.getTitreTache(); // Demande à la vue d'obtenir le titre
        String description = mainView.getDescriptionTache(); // Demande à la vue d'obtenir la description
        LocalDate echeance = mainView.getEcheance(); // Demande la date d'échéance
        boolean statut = mainView.getStatut(); // Demande le statut

        Tache tache = new Tache(id, titre, description, echeance, statut);
        tacheRepository.add(tache); // Ajoute la tâche au modèle
        mainView.afficherMessage("Tâche ajoutée !"); // Notifie l'utilisateur via la vue
    }

    /**
     * Supprime une tâche existante en fonction de son ID.
     */
    public void supprimerTache() {
        int id = mainView.getIdTache(); // Récupère l'ID de la tâche

        Optional<Tache> tache = tacheRepository.getById(id);
        if (tache.isPresent()) {
            tacheRepository.remove(tache.get());
            mainView.afficherMessage("Tâche supprimée !"); // Notifie l'utilisateur via la vue
        } else {
            mainView.afficherMessage("Tâche non trouvée."); // Notifie l'utilisateur en cas d'erreur
        }
    }

    /**
     * Modifie une tâche existante en fonction de son ID.
     */
    public void modifierTache() {
        try {
            // Récupération des données de la vue
            int id = mainView.getIdTache(); // Récupère l'ID
            String nouveauTitre = mainView.getTitreTache(); // Récupère le nouveau titre
            String nouvelleDescription = mainView.getDescriptionTache(); // Récupère la nouvelle description
            LocalDate nouvelleDateEcheance = mainView.getEcheance(); // Récupère la nouvelle date d'échéance
            boolean nouveauStatut = mainView.getStatut(); // Récupère le statut choisi par l'utilisateur

            // Recherche de la tâche existante
            Optional<Tache> tache = tacheRepository.getById(id);
            if (tache.isPresent()) {
                Tache updatedTache = new Tache(id, nouveauTitre, nouvelleDescription, nouvelleDateEcheance, nouveauStatut);
                tacheRepository.update(id, updatedTache);
                mainView.afficherMessage("Tâche modifiée avec succès !");
            } else {
                mainView.afficherMessage("Tâche non trouvée.");
            }
        } catch (IllegalArgumentException e) {
            mainView.afficherMessage("Erreur : " + e.getMessage());
        }
    }
}
