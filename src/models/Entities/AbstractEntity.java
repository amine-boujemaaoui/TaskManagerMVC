package models.Entities;

/**
 * Classe abstraite représentant une entité de base avec un identifiant unique.
 * 
 * <p>
 * Cette classe sert de super-classe pour toutes les entités du modèle. Elle
 * fournit un identifiant unique, commun à toutes les entités dérivées, et des
 * méthodes pour accéder et modifier cet identifiant.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public abstract class AbstractEntity {

    /**
     * Identifiant unique de l'entité.
     * 
     * <p>
     * L'identifiant permet de distinguer chaque entité de manière unique dans
     * l'application.
     * </p>
     */
    private int id;

    /**
     * Constructeur de la classe {@code AbstractEntity}.
     * 
     * <p>
     * Initialise l'entité avec un identifiant unique.
     * </p>
     *
     * @param id L'identifiant unique de l'entité.
     */
    public AbstractEntity(int id) {
        this.id = id;
    }

    /**
     * Obtient l'identifiant unique de l'entité.
     *
     * @return L'identifiant unique de l'entité.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit un nouvel identifiant unique pour l'entité.
     * 
     * <p>
     * Cette méthode permet de modifier l'identifiant de l'entité. Son utilisation
     * doit être contrôlée pour éviter des conflits d'identifiants.
     * </p>
     *
     * @param id Le nouvel identifiant unique à attribuer à l'entité.
     */
    public void setId(int id) {
        this.id = id;
    }
}
