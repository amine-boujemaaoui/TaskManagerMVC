package controllers;

/**
 * Classe abstraite définissant les méthodes de base pour tous les contrôleurs.
 * Les contrôleurs gèrent les interactions entre les modèles et les vues.
 */
public abstract class AbstractController {

    /**
     * Gère une requête spécifique basée sur l'action demandée.
     *
     * @param action une chaîne représentant l'action utilisateur.
     *               Exemples : "ajouter", "supprimer", etc.
     */
    public abstract void handleRequest(String action);

    /**
     * Met à jour la vue associée au contrôleur.
     * Cette méthode permet de refléter les changements apportés au modèle.
     */
    public abstract void updateView();
}
