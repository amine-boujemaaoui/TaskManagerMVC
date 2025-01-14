package controllers;

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
     * @param mainView la vue principale associée à ce contrôleur.
     */
    public TacheController(TacheRepository tacheRepository, MainView mainView) {
        this.tacheRepository = tacheRepository;
        this.mainView = mainView;
    }

    /**
     * Gère une action utilisateur spécifique.
     *
     * @param action une chaîne représentant l'action utilisateur.
     *               Exemples : "ajouter", "supprimer", etc.
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
            default:
                System.out.println("Action non reconnue : " + action);
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
     * Les informations sont demandées à l'utilisateur via le terminal.
     */
    public void ajouterTache() {
        System.out.print("Entrez le titre de la tâche : ");
        String titre = new java.util.Scanner(System.in).nextLine();
        System.out.print("Entrez la description : ");
        String description = new java.util.Scanner(System.in).nextLine();

        Tache tache = new Tache(titre, description);
        tacheRepository.ajouterTache(tache);
        System.out.println("Tâche ajoutée !");
    }

    /**
     * Supprime une tâche existante en fonction de son index.
     * L'index est demandé à l'utilisateur via le terminal.
     */
    public void supprimerTache() {
        System.out.print("Entrez l'index de la tâche à supprimer : ");
        int index = Integer.parseInt(new java.util.Scanner(System.in).nextLine());

        if (index >= 0 && index < tacheRepository.getToutesLesTaches().size()) {
            tacheRepository.supprimerTache(tacheRepository.getToutesLesTaches().get(index));
            System.out.println("Tâche supprimée !");
        } else {
            System.out.println("Index invalide.");
        }
    }
}
