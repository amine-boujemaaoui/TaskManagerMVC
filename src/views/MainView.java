package views;

import java.util.Scanner;

public class MainView {

    protected final Scanner scanner = new Scanner(System.in);

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

    public void afficherMenu(String titre, String[] options) {
        int largeur = 41;

        System.out.println(
            "████████╗ █████╗ ███████╗██╗  ██╗███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗██████╗ ███╗   ███╗██╗   ██╗ ██████╗\r\n" +
            "╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝██╔══██╗████╗ ████║██║   ██║██╔════╝\r\n" +
            "   ██║   ███████║███████╗█████╔╝ ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██████╔╝██╔████╔██║██║   ██║██║     \r\n" +
            "   ██║   ██╔══██║╚════██║██╔═██╗ ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██╔══██╗██║╚██╔╝██║╚██╗ ██╔╝██║     \r\n" +
            "   ██║   ██║  ██║███████║██║  ██╗██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║  ██║██║ ╚═╝ ██║ ╚████╔╝ ╚██████╗\r\n" +
            "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝  ╚═══╝   ╚═════╝\r\n" +
            "\r\n");
        System.out.println("┌" + "─".repeat(largeur - 2) + "┐");
        System.out.printf("│ %-37s │%n", titre);
        System.out.println("├" + "─".repeat(largeur - 2) + "┤");

        for (String option : options) {
            System.out.printf("│ %-37s │%n", option);
        }

        System.out.println("└" + "─".repeat(largeur - 2) + "┘");
    }

    public String afficherEtLire(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
