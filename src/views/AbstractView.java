package views;

/**
 * Classe abstraite définissant la base pour toutes les vues de l'application.
 * Fournit des méthodes générales pour afficher les informations et capturer
 * les interactions utilisateur.
 */
public abstract class AbstractView {

    /**
     * Méthode pour afficher les informations à l'utilisateur.
     * Chaque vue concrète doit implémenter cette méthode pour gérer son affichage.
     */
    public abstract void display();

    /**
     * Méthode pour capturer les entrées utilisateur.
     * Cette méthode peut être laissée vide pour les vues qui n'ont pas besoin d'interaction.
     *
     * @return une chaîne représentant l'entrée utilisateur, ou {@code null} si non utilisée.
     */
    public String getInput() {
        return null; // Par défaut, aucune entrée utilisateur
    }
}
