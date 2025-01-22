package views;

import java.util.Scanner;

public abstract class AbstractView {
    protected final Scanner scanner = new Scanner(System.in);

    /**
     * Affiche un message à l'utilisateur et capture sa réponse.
     * 
     * @param message Le message à afficher.
     * @return La réponse saisie par l'utilisateur.
     */
    public String afficherEtLire(String message) {
        System.out.println(message);
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Affiche un message simple.
     * 
     * @param message Le message à afficher.
     */
    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
