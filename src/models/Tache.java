package models;

import java.time.LocalDate;

/**
 * Classe représentant une tâche dans l'application.
 * Contient les attributs et les comportements associés à une tâche.
 */
public class Tache {

    private String titre;
    private String description;
    private LocalDate echeance;
    private boolean statut;

    /**
     * Constructeur pour créer une nouvelle tâche.
     *
     * @param titre       le titre de la tâche.
     * @param description la description de la tâche.
     */
    public Tache(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.echeance = null; // Par défaut, pas de date d'échéance
        this.statut = false; // Par défaut, la tâche n'est pas terminée
    }
    
    /**
     * Constructeur pour créer une nouvelle tâche.
     *
     * @param titre       le titre de la tâche.
     * @param description la description de la tâche.
     * @param echeance    la date d'échéance de la tâche, peut être null.
     * @param statut      le statut de la tâche (terminée ou non).
     */
    public Tache(String titre, String description, LocalDate echeance, boolean statut) {
        this.titre = titre;
        this.description = description;
        this.echeance = echeance; // Peut être null
        this.statut = statut;
    }

    /**
     * Retourne le titre de la tâche.
     *
     * @return le titre.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit le titre de la tâche.
     *
     * @param titre le nouveau titre.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Retourne la description de la tâche.
     *
     * @return la description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de la tâche.
     *
     * @param description la nouvelle description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne la date d'échéance de la tâche.
     *
     * @return la date d'échéance.
     */
    public LocalDate getEcheance() {
        return echeance;
    }

    /**
     * Définit la date d'échéance de la tâche.
     *
     * @param echeance la nouvelle date d'échéance.
     */
    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    /**
     * Retourne le statut de la tâche (terminée ou non).
     *
     * @return vrai si la tâche est terminée, sinon faux.
     */
    public boolean isStatut() {
        return statut;
    }

    /**
     * Définit le statut de la tâche.
     *
     * @param statut vrai si la tâche est terminée, sinon faux.
     */
    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Tâche{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", echeance=" + (echeance != null ? echeance : "Non définie") +
                ", statut=" + (statut ? "Terminée" : "Non terminée") +
                '}';
    }
}
