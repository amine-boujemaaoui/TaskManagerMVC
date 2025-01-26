package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaces.Observateur;
import models.Entities.AbstractEntity;

/**
 * Classe abstraite représentant une vue générique pour interagir avec les
 * entités.
 * Fournit des méthodes communes pour afficher, modifier et supprimer des
 * entités.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 *
 * @param <T> Le type d'entité qui étend {@link AbstractEntity}.
 */
public abstract class AbstractView<T extends AbstractEntity> implements Observateur {

    /**
     * Scanner pour lire les entrées utilisateur.
     */
    protected final Scanner scanner = new Scanner(System.in);

    /**
     * Permet à l'utilisateur d'ajouter une nouvelle entité.
     *
     * @param id L'identifiant unique pour la nouvelle entité.
     * @return L'entité créée par l'utilisateur.
     */
    public abstract T ajouter(int id);

    /**
     * Permet à l'utilisateur de modifier une entité existante.
     *
     * @param entity L'entité à modifier.
     * @return L'entité modifiée.
     */
    public abstract T modifier(T entity);

    /**
     * Permet à l'utilisateur de supprimer une entité.
     */
    public abstract void supprimer();

    /**
     * Affiche une liste d'entités.
     *
     * @param entites La liste des entités à afficher.
     */
    public abstract void afficherTous(List<T> entites);

    /**
     * Affiche les détails d'une entité spécifique.
     *
     * @param entity L'entité dont les détails doivent être affichés.
     */
    public abstract void afficherDetails(T entity);

    /**
     * Affiche un message et lit l'entrée utilisateur.
     *
     * @param message Le message à afficher.
     * @return La ligne saisie par l'utilisateur.
     */
    public String afficherEtLire(String message) {
        afficherMessage(message);
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

    /**
     * Affiche un menu avec un titre et une liste d'options.
     *
     * @param titre   Le titre du menu.
     * @param options Les options disponibles dans le menu.
     */
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

    /**
     * Affiche un menu et lit le choix de l'utilisateur.
     *
     * @return Le choix de l'utilisateur sous forme de chaîne.
     */
    public abstract String afficherMenuEtLireChoix();

    /**
     * Demande à l'utilisateur de saisir un identifiant pour une action donnée.
     *
     * @param action La description de l'action à effectuer.
     * @return L'identifiant saisi par l'utilisateur.
     */
    public int demanderId(String action) {
        return Integer.parseInt(afficherEtLire("Entrez l'ID de l'entité à " + action + " :"));
    }

    /**
     * Tronque une chaîne de caractères si elle dépasse une certaine largeur.
     *
     * @param texte   La chaîne à tronquer.
     * @param largeur La largeur maximale.
     * @return La chaîne tronquée si nécessaire, ou la chaîne originale avec des
     *         espaces ajoutés.
     */
    public String tronquer(String texte, int largeur) {
        if (texte.length() > largeur) {
            return texte.substring(0, largeur - 3) + "...";
        }
        return String.format("%-" + largeur + "s", texte);
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
