import controllers.MainController;
import controllers.MissionController;
import controllers.ProjetController;
import controllers.TacheController;
import models.Repositories.MissionRepository;
import models.Repositories.ProjetRepository;
import models.Repositories.TacheRepository;
import views.MissionView;
import views.ProjetView;
import views.TacheView;

/**
 * Classe principale représentant le point d'entrée de l'application.
 * Elle initialise les référentiels, les vues, les contrôleurs et
 * orchestre leur interaction via le contrôleur principal.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class Main {

    /**
     * Méthode principale qui démarre l'application.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {

        // Initialisation des référentiels pour les tâches, missions et projets
        TacheRepository tacheRepository = new TacheRepository("resources/taches.csv");
        MissionRepository missionRepository = new MissionRepository("resources/missions.csv");
        ProjetRepository projetRepository = new ProjetRepository("resources/projets.csv");

        // Initialisation des vues pour les tâches, missions et projets
        TacheView tacheView = new TacheView();
        MissionView missionView = new MissionView();
        ProjetView projetView = new ProjetView();

        // Enregistrement des observateurs (les vues) sur les référentiels
        tacheRepository.ajouterObservateur(tacheView);
        missionRepository.ajouterObservateur(missionView);
        projetRepository.ajouterObservateur(projetView);

        // Initialisation des contrôleurs pour les tâches, missions et projets
        TacheController tacheController = new TacheController(tacheView, tacheRepository);
        MissionController missionController = new MissionController(missionView, missionRepository);
        ProjetController projetController = new ProjetController(projetView, projetRepository, missionController, tacheController);

        // Initialisation du contrôleur principal pour gérer les interactions utilisateur
        MainController mainController = new MainController();

        // Enregistrement des contrôleurs dans le contrôleur principal avec leurs identifiants
        mainController.enregistrerController("1", tacheController);
        mainController.enregistrerController("2", missionController);
        mainController.enregistrerController("3", projetController);

        // Démarrage de l'application via le contrôleur principal
        mainController.executer();
    }
}
