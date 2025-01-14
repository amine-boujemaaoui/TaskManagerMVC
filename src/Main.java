import controllers.TacheController;
import models.TacheRepository;
import views.*;

/**
 * Point d'entrée de l'application.
 */
public class Main {

    public static void main(String[] args) {
        Application app = new Application(); // Initialisation de l'application
        app.run(); // Lancement de l'application
    }
}
