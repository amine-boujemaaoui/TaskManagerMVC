import controllers.MainController;
import controllers.MissionController;
import controllers.ProjetController;
import controllers.TacheController;
import models.MissionRepository;
import models.ProjetRepository;
import models.TacheRepository;
import views.MissionView;
import views.ProjetView;
import views.TacheView;

/**
 * Point d'entrée de l'application.
 */
public class Main {
    public static void main(String[] args) {
        // Initialiser les référentiels
        MissionRepository missionRepository = new MissionRepository();
        TacheRepository tacheRepository = new TacheRepository();
        ProjetRepository projetRepository = new ProjetRepository();

        // Initialiser les contrôleurs
        MissionController missionController = new MissionController(missionRepository, new MissionView());
        TacheController tacheController = new TacheController(tacheRepository, new TacheView());
        ProjetController projetController = new ProjetController(projetRepository, new ProjetView(), missionController);

        // Initialiser le contrôleur principal
        MainController mainController = new MainController();
        mainController.enregistrerController("1", tacheController);
        mainController.enregistrerController("2", missionController);
        mainController.enregistrerController("3", projetController);

        // Exécuter l'application
        mainController.executer();
    }
}
