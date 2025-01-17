package views;

import java.util.Scanner;

/**
 * Vue pour afficher le panneau des commandes disponibles.
 */
public class CommandPanelView extends AbstractView {

    private final Scanner scanner; // Scanner partagé

    /**
     * Constructeur pour initialiser la vue avec un scanner partagé.
     *
     * @param scanner le scanner partagé pour les entrées utilisateur.
     */
    public CommandPanelView(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Affiche les commandes disponibles à l'utilisateur.
     */
    @Override
    public void display() {
        System.out.println("┌" + "─".repeat(30) + "┐");
        System.out.println("│ Commandes disponibles        │");
        System.out.println("├" + "─".repeat(30) + "┤");
        System.out.println("│ 1. Ajouter une tâche         │");
        System.out.println("│ 2. Supprimer une tâche       │");
        System.out.println("│ 3. Quitter                   │");
        System.out.println("└" + "─".repeat(30) + "┘");
    }

    /**
     * Capture l'entrée utilisateur pour choisir une commande.
     *
     * @return une chaîne représentant le choix de l'utilisateur.
     */
    @Override
    public String getInput() {
        System.out.print("\nEntrez une commande : ");
        return scanner.nextLine(); // Utilise le scanner partagé
    }
}
