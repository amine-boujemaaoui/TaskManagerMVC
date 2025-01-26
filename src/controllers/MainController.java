package controllers;

import views.MainView;

import java.util.HashMap;
import java.util.Map;

/**
 * Contrôleur principal chargé de gérer le menu principal et de diriger
 * l'exécution vers les contrôleurs spécifiques en fonction du choix de
 * l'utilisateur.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class MainController {

    /**
     * Vue associée pour afficher le menu principal et interagir avec l'utilisateur.
     */
    private final MainView mainView;

    /**
     * Map contenant les contrôleurs enregistrés.
     * Chaque contrôleur est associé à une clé unique (par exemple, "1" pour les
     * tâches).
     */
    private final Map<String, AbstractController<?>> controllers;

    /**
     * Constructeur pour initialiser le contrôleur principal.
     * Initialise la vue principale et la collection des contrôleurs.
     */
    public MainController() {
        this.mainView = new MainView();
        this.controllers = new HashMap<>();
    }

    /**
     * Enregistre un contrôleur avec un identifiant unique.
     * Cette méthode permet de lier un contrôleur spécifique à une option du menu
     * principal.
     *
     * @param key        L'identifiant du contrôleur (par exemple, "1" pour les
     *                   tâches).
     * @param controller Le contrôleur à enregistrer.
     */
    public void enregistrerController(String key, AbstractController<?> controller) {
        controllers.put(key, controller);
    }

    /**
     * Démarre la boucle principale du programme.
     * Affiche le menu principal, attend le choix de l'utilisateur, et redirige vers
     * le contrôleur correspondant si une option valide est choisie.
     * L'utilisateur peut quitter le programme en sélectionnant l'option "0".
     */
    public void executer() {
        boolean running = true;

        while (running) {
            // Affiche le menu principal et récupère le choix de l'utilisateur
            String choix = mainView.afficherMenuEtLireChoix();

            if (choix.equals("0")) {
                // Quitte la boucle et affiche un message d'au revoir
                running = false;
                mainView.afficherMessage("Au revoir !");
            } else {
                // Récupère le contrôleur associé au choix
                AbstractController<?> controller = controllers.get(choix);
                if (controller != null) {
                    // Exécute le contrôleur correspondant
                    controller.executer();
                } else {
                    // Affiche un message d'erreur si l'option est invalide
                    mainView.afficherMessage("Option invalide, veuillez réessayer.");
                }
            }
        }
    }
}
