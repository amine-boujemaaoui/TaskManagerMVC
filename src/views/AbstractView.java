package views;

import java.util.Scanner;

/**
 * Classe abstraite représentant une vue générique pour interagir avec
 * l'utilisateur.
 * Fournit des méthodes utilitaires pour afficher des messages, des menus et
 * capturer
 * les entrées de l'utilisateur.
 */
public abstract class AbstractView {

    /**
     * Scanner utilisé pour capturer les entrées utilisateur.
     */
    protected final Scanner scanner = new Scanner(System.in);

    /**
     * Affiche un message à l'utilisateur et capture sa réponse.
     *
     * @param message Le message à afficher.
     * @return La réponse saisie par l'utilisateur.
     */
    public String afficherEtLire(String message) {
        System.out.print(message + " > ");
        return scanner.nextLine();
    }

    /**
     * Affiche un message simple à l'utilisateur.
     *
     * @param message Le message à afficher.
     */
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    /**
     * Affiche un menu avec des options numérotées sous forme de tableau.
     *
     * @param titre   Le titre du menu.
     * @param options Les options du menu à afficher.
     */
    public void afficherMenu(String titre, String[] options) {
        int largeur = 35; // Largeur totale du tableau
        System.out.println("┌" + "─".repeat(largeur - 2) + "┐");
        System.out.printf("│ %-31s │%n", titre);
        System.out.println("├" + "─".repeat(largeur - 2) + "┤");

        for (String option : options) {
            System.out.printf("│ %-31s │%n", option);
        }

        System.out.println("└" + "─".repeat(largeur - 2) + "┘");
    }

    /**
     * Efface la console pour améliorer la lisibilité.
     * Compatible avec Windows et les systèmes basés sur Unix.
     */
    public void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Impossible de nettoyer la console.");
        }
    }
}
