package models.Entities;

import java.time.LocalDate;

/**
 * Classe représentant une tâche avec un titre, une description, une date
 * d'échéance et un statut.
 * <p>
 * Une tâche est identifiée de manière unique par un identifiant, hérité de la
 * classe {@link AbstractEntity}. Elle contient des informations sur son titre,
 * sa description, sa date d'échéance et son statut (terminée ou en cours).
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class Tache extends AbstractEntity {

    /**
     * Le titre de la tâche, décrivant brièvement son contenu ou objectif.
     */
    private String titre;

    /**
     * Une description détaillée de la tâche, fournissant plus d'informations sur
     * son contenu.
     */
    private String description;

    /**
     * La date limite à laquelle la tâche doit être terminée.
     */
    private LocalDate echeance;

    /**
     * Le statut de la tâche :
     * {@code true} si la tâche est terminée,
     * {@code false} si elle est en cours.
     */
    private boolean statut;

    /**
     * Constructeur de la classe {@code Tache}.
     *
     * @param id          L'identifiant unique de la tâche.
     * @param titre       Le titre de la tâche.
     * @param description Une description détaillée de la tâche.
     * @param echeance    La date d'échéance à laquelle la tâche doit être
     *                    terminée.
     * @param statut      Le statut indiquant si la tâche est terminée
     *                    ({@code true}) ou en cours ({@code false}).
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
     * Définit un nouveau titre pour la tâche.
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
     * Définit une nouvelle description pour la tâche.
     *
     * @param description La nouvelle description de la tâche.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtient la date d'échéance de la tâche.
     *
     * @return La date d'échéance de la tâche, ou {@code null} si aucune date
     *         n'est définie.
     */
    public LocalDate getEcheance() {
        return echeance;
    }

    /**
     * Définit une nouvelle date d'échéance pour la tâche.
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
     *               {@code false} pour indiquer qu'elle est en cours.
     */
    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    /**
     * Retourne une représentation textuelle de la tâche.
     *
     * @return Une chaîne contenant les informations détaillées de la tâche, y
     *         compris son identifiant, son titre, sa description, sa date
     *         d'échéance et son statut.
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
