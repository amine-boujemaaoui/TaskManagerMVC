package models;

/**
 * Classe abstraite représentant une entité de base avec un identifiant unique.
 */
public abstract class AbstractEntity {

    /**
     * Identifiant unique de l'entité.
     */
    private int id;

    /**
     * Constructeur de la classe {@code AbstractEntity}.
     *
     * @param id L'identifiant unique de l'entité.
     */
    public AbstractEntity(int id) {
        this.id = id;
    }

    /**
     * Obtient l'identifiant de l'entité.
     *
     * @return L'identifiant unique de l'entité.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit un nouvel identifiant pour l'entité.
     *
     * @param id Le nouvel identifiant unique.
     */
    public void setId(int id) {
        this.id = id;
    }
}
