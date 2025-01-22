import controllers.TacheController;
import models.TacheRepository;
import views.TacheView;

/**
 * Point d'entrée de l'application TaskManagerMVC.
 * Initialise les composants principaux (modèle, vue, contrôleur) et lance
 * l'exécution.
 */
public class Main {

    /**
     * Méthode principale qui démarre l'application.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans cette
     *             application).
     */
    public static void main(String[] args) {
        // Initialisation du contrôleur avec le référentiel de tâches et la vue
        // correspondante
        TacheController tacheController = new TacheController(new TacheRepository(), new TacheView());

        // Démarrage du contrôleur
        tacheController.executer();
    }
}
