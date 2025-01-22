import java.util.Scanner;

import controllers.TacheController;
import models.TacheRepository;
import utils.TacheFileUtil;
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

        // Initier le repository et le file util
        TacheRepository tacheRepository = new TacheRepository();
        TacheFileUtil tacheFileUtil = new TacheFileUtil("save.json");

        try {
            // Charger les tâches depuis le fichier
            tacheRepository.addAll(tacheFileUtil.charger());
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des tâches : " + e.getMessage());
        }

        ListeTachesView listeTachesView = new ListeTachesView(tacheRepository.getAll());
        MainView mainView = new MainView(listeTachesView, new CommandPanelView(scanner));

        tacheRepository.addObserver(listeTachesView);

        this.tacheController = new TacheController(tacheRepository, mainView, tacheFileUtil);
    }

    public void run() {
        tacheController.start();

        // Sauvegarder les tâches avant de quitter l'application
        try {
            tacheController.sauvegarderTaches();
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde des tâches : " + e.getMessage());
        }
    }
}
