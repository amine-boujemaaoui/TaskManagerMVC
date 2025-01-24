package views;

public class MainView extends AbstractView {

    /**
     * Affiche le menu principal de l'application.
     * 
     * @return Le choix de l'utilisateur.
     */
    public String afficherMenuPrincipal() {
        clearConsole();
        String[] options = {
                "1. Gérer les tâches",
                "2. Gérer les missions",
                "3. Gérer les projets",
                "0. Quitter"
        };
        afficherMenu("Menu principal", options);
        return afficherEtLire("Choix");
    }
}
