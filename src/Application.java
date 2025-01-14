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

    /**
     * Constructeur de l'application.
     * Initialise le modèle, les vues et le contrôleur.
     */
    public Application() {
        // Initialisation du modèle
        TacheRepository tacheRepository = new TacheRepository();

        // Initialisation des vues
        ListeTachesView listeTachesView = new ListeTachesView(tacheRepository.getToutesLesTaches());
        CommandPanelView commandPanelView = new CommandPanelView();
        MainView mainView = new MainView(listeTachesView, commandPanelView);

        // Enregistrement de la vue comme observateur
        tacheRepository.addObserver(listeTachesView);

        // Initialisation du contrôleur
        this.tacheController = new TacheController(tacheRepository, mainView);
    }

    /**
     * Méthode pour lancer l'application.
     * Gère la boucle principale et les interactions utilisateur.
     */
    public void run() {
        boolean running = true;
        while (running) {
            tacheController.updateView(); // Affiche la vue principale
            String input = tacheController.getUserInput(); // Capture l'entrée utilisateur

            switch (input) {
                case "1":
                    tacheController.ajouterTache();
                    break;
                case "2":
                    tacheController.supprimerTache();
                    break;
                case "3":
                    running = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Commande non reconnue. Essayez encore.");
            }
        }
    }
}
