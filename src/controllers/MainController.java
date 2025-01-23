package controllers;

import views.MainView;

public class MainController {

    private final MainView mainView;
    private final TacheController tacheController;
    private final MissionController missionController; // À créer pour Mission

    public MainController(TacheController tacheController, MissionController missionController) {
        this.mainView = new MainView();
        this.tacheController = tacheController;
        this.missionController = missionController;
    }

    /**
     * Démarre le contrôleur principal.
     */
    public void executer() {
        boolean running = true;

        while (running) {
            String choix = mainView.afficherMenuPrincipal();

            switch (choix) {
                case "1" -> tacheController.executer(); // Gérer les tâches
                case "2" -> missionController.executer(); // Gérer les missions
                case "0" -> {
                    running = false;
                    mainView.afficherMessage("Au revoir !");
                }
                default -> mainView.afficherMessage("Option invalide, veuillez réessayer.");
            }
        }
    }
}
