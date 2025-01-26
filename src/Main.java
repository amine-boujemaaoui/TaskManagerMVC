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

        TacheRepository tacheRepository = new TacheRepository("resources/taches.csv");
        MissionRepository missionRepository = new MissionRepository("resources/missions.csv");
        ProjetRepository projetRepository = new ProjetRepository("resources/projets.csv");

        TacheView tacheView = new TacheView();
        MissionView missionView = new MissionView();
        ProjetView projetView = new ProjetView();

        tacheRepository.ajouterObservateur(tacheView);
        missionRepository.ajouterObservateur(missionView);
        projetRepository.ajouterObservateur(projetView);

        TacheController tacheController = new TacheController(tacheView, tacheRepository);
        MissionController missionController = new MissionController(missionView, missionRepository);
        ProjetController projetController = new ProjetController(projetView, projetRepository, missionController);

        MainController mainController = new MainController();
        mainController.enregistrerController("1", tacheController);
        mainController.enregistrerController("2", missionController);
        mainController.enregistrerController("3", projetController);

        mainController.executer();
    }
}
