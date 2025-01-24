package controllers;

import views.MainView;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    private final MainView mainView;
    private final Map<String, AbstractController<?>> controllers;

    public MainController() {
        this.mainView = new MainView();
        this.controllers = new HashMap<>();
    }

    /**
     * Enregistre un contrôleur avec un identifiant unique.
     *
     * @param key        L'identifiant du contrôleur (par exemple, "1" pour les
     *                   tâches).
     * @param controller Le contrôleur à enregistrer.
     */
    public void enregistrerController(String key, AbstractController<?> controller) {
        controllers.put(key, controller);
    }

    /**
     * Démarre le contrôleur principal.
     */
    public void executer() {
        boolean running = true;

        while (running) {
            String choix = mainView.afficherMenuPrincipal();

            if (choix.equals("0")) {
                running = false;
                mainView.afficherMessage("Au revoir !");
            } else {
                AbstractController<?> controller = controllers.get(choix);
                if (controller != null) {
                    controller.executer();
                } else {
                    mainView.afficherMessage("Option invalide, veuillez réessayer.");
                }
            }
        }
    }
}
