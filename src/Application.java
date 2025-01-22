import controllers.TacheController;
import models.TacheRepository;
import models.Tache;
import utils.TacheFileUtil;
import views.TacheView;

import java.util.List;

/**
 * Classe principale pour gérer le cycle de vie de l'application.
 * Initialise les modèles, vues et contrôleurs, et orchestre leur interaction.
 */
public class Application {

    /**
     * Démarre l'application.
     */
    public void run() {

        TacheFileUtil tacheFileUtil = new TacheFileUtil("save.json");

        TacheRepository tacheRepository = new TacheRepository();
        TacheView tacheView = new TacheView();
        TacheController tacheController = new TacheController(tacheRepository, tacheView);

        try {
            List<Tache> taches = tacheFileUtil.charger();
            tacheRepository.ajouterToutesLesEntites(taches);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des tâches : " + e.getMessage());
        }

        tacheController.executer();

        try {
            tacheFileUtil.sauvegarder(tacheController.getRepository().getToutesLesEntites());
            System.out.println("Tâches sauvegardées avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde des tâches : " + e.getMessage());
        }
    }
}
