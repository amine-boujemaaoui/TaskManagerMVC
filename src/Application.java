import java.util.Scanner;

import controllers.TacheController;
import models.TacheRepository;
import views.CommandPanelView;
import views.ListeTachesView;
import views.MainView;

/**
 * Classe principale pour gérer le cycle de vie de l'application.
 * Initialise les modèles, vues et contrôleurs, et orchestre leur interaction.
 */
public class Application {

    private final TacheController tacheController;

    public Application() {

        Scanner scanner = new Scanner(System.in); // Scanner partagé

        TacheRepository tacheRepository = new TacheRepository();

        ListeTachesView listeTachesView = new ListeTachesView(tacheRepository.getToutesLesTaches());
        MainView mainView = new MainView(listeTachesView, new CommandPanelView(scanner));

        tacheRepository.addObserver(listeTachesView); // Enregistrement de la vue comme observateur

        this.tacheController = new TacheController(tacheRepository, mainView);
    }

    public void run() {
        tacheController.start();
    }
}
