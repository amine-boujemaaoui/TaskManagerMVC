package views;

/**
 * Vue principale qui combine plusieurs sous-vues pour gérer l'affichage global.
 */
public class MainView extends AbstractView {

    private final ListeTachesView listeTachesView;
    private final CommandPanelView commandPanelView;

    /**
     * Constructeur pour initialiser les sous-vues de la vue principale.
     *
     * @param listeTachesView   la vue pour afficher la liste des tâches.
     * @param commandPanelView  la vue pour afficher les commandes disponibles.
     */
    public MainView(ListeTachesView listeTachesView, CommandPanelView commandPanelView) {
        this.listeTachesView = listeTachesView;
        this.commandPanelView = commandPanelView;
    }

    /**
     * Affiche les sous-vues dans un ordre défini.
     */
    @Override
    public void display() {
        System.out.println("\n--- Gestionnaire de Tâches ---");
        listeTachesView.display();
        commandPanelView.display();
    }

    /**
     * Capture l'entrée utilisateur via le panneau de commandes.
     *
     * @return une chaîne représentant l'entrée utilisateur.
     */
    @Override
    public String getInput() {
        return commandPanelView.getInput();
    }
}
