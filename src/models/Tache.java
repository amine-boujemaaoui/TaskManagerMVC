package models;

import java.time.LocalDate;

public class Tache extends AbstractEntity {
    private String titre;
    private String description;
    private LocalDate echeance;
    private boolean statut;

    public Tache(int id, String titre, String description, LocalDate echeance, boolean statut) {
        super(id);
        this.titre = titre;
        this.description = description;
        this.echeance = echeance;
        this.statut = statut;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
}
