package models;

import java.time.LocalDate;

/**
 * Classe représentant une tâche avec un titre, une description, une échéance et
 * un statut.
 */
public class Tache extends AbstractEntity {

    /**
     * Le titre de la tâche.
     */
    private String titre;

    /**
     * La description de la tâche.
     */
    private String description;

    /**
     * La date d'échéance de la tâche.
     */
    private LocalDate echeance;

    /**
     * Le statut de la tâche, indiquant si elle est terminée (true) ou en cours
     * (false).
     */
    private boolean statut;

    /**
     * Constructeur de la classe {@code Tache}.
     *
     * @param id          L'identifiant unique de la tâche.
     * @param titre       Le titre de la tâche.
     * @param description La description de la tâche.
     * @param echeance    La date d'échéance de la tâche.
     * @param statut      Le statut de la tâche (terminée ou non).
     */
    public Tache(int id, String titre, String description, LocalDate echeance, boolean statut) {
        super(id);
        this.titre = titre;
        this.description = description;
        this.echeance = echeance;
        this.statut = statut;
    }

    /**
     * Obtient le titre de la tâche.
     *
     * @return Le titre de la tâche.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit le titre de la tâche.
     *
     * @param titre Le nouveau titre de la tâche.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Obtient la description de la tâche.
     *
     * @return La description de la tâche.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de la tâche.
     *
     * @param description La nouvelle description de la tâche.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtient la date d'échéance de la tâche.
     *
     * @return La date d'échéance de la tâche.
     */
    public LocalDate getEcheance() {
        return echeance;
    }

    /**
     * Définit la date d'échéance de la tâche.
     *
     * @param echeance La nouvelle date d'échéance de la tâche.
     */
    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    /**
     * Indique si la tâche est terminée.
     *
     * @return {@code true} si la tâche est terminée, {@code false} sinon.
     */
    public boolean isStatut() {
        return statut;
    }

    /**
     * Définit le statut de la tâche.
     *
     * @param statut {@code true} pour indiquer que la tâche est terminée,
     *               {@code false} sinon.
     */
    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    /**
     * Retourne une représentation textuelle de la tâche.
     *
     * @return Une chaîne représentant les informations de la tâche.
     */
    @Override
    public String toString() {
        return "Tache{" +
                "id=" + getId() +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", echeance=" + echeance +
                ", statut=" + statut +
                '}';
    }
}
