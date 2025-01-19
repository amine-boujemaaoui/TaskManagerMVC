package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Vue principale qui combine plusieurs sous-vues pour gérer l'affichage global.
 */
public class MainView extends AbstractView {

    private final ListeTachesView listeTachesView;
    private final CommandPanelView commandPanelView;
    private final Scanner scanner; // Scanner partagé pour toutes les entrées utilisateur

    /**
     * Constructeur pour initialiser les sous-vues de la vue principale.
     *
     * @param listeTachesView  la vue pour afficher la liste des tâches.
     * @param commandPanelView la vue pour afficher les commandes disponibles.
     */
    public MainView(ListeTachesView listeTachesView, CommandPanelView commandPanelView) {
        this.listeTachesView = listeTachesView;
        this.commandPanelView = commandPanelView;
        this.scanner = new Scanner(System.in); // Initialisation du Scanner partagé
    }

    /**
     * Affiche les sous-vues dans un ordre défini.
     */
    @Override
    public void display() {
        clearConsole();
        commandPanelView.display();
        listeTachesView.display();
    }

    /**
     * Efface la console d'affichage.
     */
    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'effacement de la console.");
        }
    }

    /**
     * Demande à l'utilisateur d'entrer le titre de la tâche.
     *
     * @return le titre saisi par l'utilisateur.
     */
    public String getTitreTache() {
        System.out.print("Entrez le titre de la tâche : ");
        return scanner.nextLine(); // Utilise le scanner partagé
    }

    /**
     * Demande à l'utilisateur d'entrer la description de la tâche.
     *
     * @return la description saisie par l'utilisateur.
     */
    public String getDescriptionTache() {
        System.out.print("Entrez la description : ");
        return scanner.nextLine(); // Utilise le scanner partagé
    }

    /**
     * Affiche un message à l'utilisateur.
     *
     * @param message le message à afficher.
     */
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    /**
     * Demande à l'utilisateur d'entrer l'index de la tâche à supprimer.
     *
     * @return l'index de la tâche saisi par l'utilisateur.
     */
    public int getIndexTache() {
        System.out.print("Entrez l'index de la tâche à supprimer : ");
        try {
            return Integer.parseInt(scanner.nextLine()); // Utilise le scanner partagé
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            return -1; // Retourne une valeur invalide pour signaler une erreur
        }
    }

    public LocalDate getEcheance() {
        System.out.print("Entrez la date d'échéance (format: AAAA-MM-JJ) ou laissez vide pour aucune : ");
        String input = scanner.nextLine();

        if (input.trim().isEmpty()) {
            return null; // Pas de date d'échéance
        }

        try {
            return LocalDate.parse(input.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("Format invalide. Veuillez entrer une date au format AAAA-MM-JJ.");
            return getEcheance(); // Relance la saisie
        }
    }

    public boolean getStatut() {
        System.out.print("Marquer la tâche comme terminée ? (oui/non) : ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("oui"); // Retourne true si l'utilisateur tape "oui", sinon false
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
