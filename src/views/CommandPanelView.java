package views;

import java.util.Scanner;

/**
 * Vue pour afficher le panneau des commandes disponibles.
 */
public class CommandPanelView extends AbstractView {

    /**
     * Affiche les commandes disponibles à l'utilisateur.
     */
    @Override
    public void display() {
        System.out.println("\n--- Commandes ---");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Supprimer une tâche");
        System.out.println("3. Quitter");
    }

    /**
     * Capture l'entrée utilisateur pour choisir une commande.
     *
     * @return une chaîne représentant le choix de l'utilisateur.
     */
    @Override
    public String getInput() {
        System.out.print("\nEntrez une commande : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
