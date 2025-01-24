package models;

import java.util.List;

/**
 * Classe représentant un projet avec un nom, une description, une mission et
 * des tâches.
 */
public class Projet extends AbstractEntity {

    private String nom;
    private String description;
    private Mission mission;
    private List<Tache> taches;

    public Projet(int id, String nom, String description, Mission mission, List<Tache> taches) {
        super(id);
        this.nom = nom;
        this.description = description;
        this.mission = mission;
        this.taches = taches;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + getId() +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", mission=" + mission +
                ", taches=" + taches +
                '}';
    }
}
