import controllers.MainController;
import controllers.MissionController;
import controllers.TacheController;
import models.MissionRepository;
import models.TacheRepository;
import views.MissionView;
import views.TacheView;

/**
 * Point d'entrée de l'application.
 */
public class Main {
    public static void main(String[] args) {
        // Initialiser les composants
        TacheController tacheController = new TacheController(new TacheRepository(), new TacheView());
        MissionController missionController = new MissionController(new MissionRepository(), new MissionView());

        // Initialiser le contrôleur principal
        MainController mainController = new MainController(tacheController, missionController);

        // Exécuter l'application
        mainController.executer();
    }
}
