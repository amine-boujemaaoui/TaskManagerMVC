package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaces.Observateur;
import models.AbstractEntity;

public abstract class AbstractView<T extends AbstractEntity> implements Observateur {

    protected final Scanner scanner = new Scanner(System.in);

    public abstract T ajouter(int id);

    public abstract T modifier(T entity);

    public abstract void supprimer();

    public abstract void afficherTous(List<T> entites);

    public abstract void afficherDetails(T entity);

    public String afficherEtLire(String message) {
        afficherMessage(message);
        return scanner.nextLine();
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public void afficherMenu(String titre, String[] options) {
        int largeur = 41;
        System.out.println("┌" + "─".repeat(largeur - 2) + "┐");
        System.out.printf("│ %-37s │%n", titre);
        System.out.println("├" + "─".repeat(largeur - 2) + "┤");

        for (String option : options) {
            System.out.printf("│ %-37s │%n", option);
        }

        System.out.println("└" + "─".repeat(largeur - 2) + "┘");
    }

    public abstract String afficherMenuEtLireChoix();

    public int demanderId(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID de l'entité à " + action + " :"));
    }

    public String tronquer(String texte, int largeur) {
        if (texte.length() > largeur) {
            return texte.substring(0, largeur - 3) + "...";
        }
        return String.format("%-" + largeur + "s", texte);
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

    /**
     * Découpe une chaîne en plusieurs lignes de longueur maximale.
     *
     * @param texte   La chaîne à découper.
     * @param largeur La largeur maximale par ligne.
     * @return Une liste de lignes découpées.
     */
    public List<String> decouperEnLignes(String texte, int largeur) {
        List<String> lignes = new ArrayList<>();
        int index = 0;

        while (index < texte.length()) {
            int fin = Math.min(index + largeur, texte.length());
            lignes.add(texte.substring(index, fin));
            index = fin;
        }

        return lignes;
    }
}
