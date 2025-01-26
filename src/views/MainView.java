package views;

import java.util.Scanner;

/**
 * Classe représentant la vue principale de l'application.
 * Fournit un menu principal pour naviguer entre les différentes sections de
 * l'application
 * telles que la gestion des tâches, des missions et des projets.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class MainView {

    /**
     * Scanner pour lire les entrées utilisateur.
     */
    protected final Scanner scanner = new Scanner(System.in);

    /**
     * Affiche le menu principal et capture le choix de l'utilisateur.
     *
     * @return Le choix de l'utilisateur sous forme de chaîne.
     */
    public String afficherMenuEtLireChoix() {
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

    /**
     * Efface la console pour une meilleure lisibilité.
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

    /**
     * Affiche un menu avec un titre et une liste d'options, incluant un titre
     * artistique.
     *
     * @param titre   Le titre du menu.
     * @param options Les options disponibles dans le menu.
     */
    public void afficherMenu(String titre, String[] options) {
        int largeur = 41;

        // Affichage du titre artistique
        System.out.println(
                "████████╗ █████╗ ███████╗██╗  ██╗███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗██████╗ ███╗   ███╗██╗   ██╗ ██████╗\r\n"+
                "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝██╔══██╗████╗ ████║██║   ██║██╔════╝\r\n"+
                "   ██║   ███████║███████╗█████╔╝ ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██████╔╝██╔████╔██║██║   ██║██║     \r\n"+
                "   ██║   ██╔══██║╚════██║██╔═██╗ ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██╔══██╗██║╚██╔╝██║╚██╗ ██╔╝██║     \r\n"+
                "   ██║   ██║  ██║███████║██║  ██╗██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║  ██║██║ ╚═╝ ██║ ╚████╔╝ ╚██████╗\r\n"+
                "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝  ╚═══╝   ╚═════╝\r\n"+
                "\r\n");

        // Affichage du menu    
        System.out.println("┌" + "─".repeat(largeur - 2) + "┐");
        System.out.printf("│ %-37s │%n", titre);
        System.out.println("├" + "─".repeat(largeur - 2) + "┤");

        for (String option : options) {
            System.out.printf("│ %-37s │%n", option);
        }

        System.out.println("└" + "─".repeat(largeur - 2) + "┘");
    }

    /**
     * Affiche un message et lit l'entrée utilisateur.
     *
     * @param message Le message à afficher.
     * @return La chaîne saisie par l'utilisateur.
     */
    public String afficherEtLire(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * Affiche un message à l'utilisateur.
     *
     * @param message Le message à afficher.
     */
    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
