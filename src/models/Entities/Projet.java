package models.Entities;

import java.util.List;

/**
 * Classe représentant un projet avec un nom, une description, une mission et
 * une liste de tâches.
 * <p>
 * Cette classe étend {@link AbstractEntity}, ce qui lui confère un identifiant
 * unique. Un projet est associé à une {@link Mission} et peut contenir une
 * liste de {@link Tache}.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class Projet extends AbstractEntity {

    /**
     * Le nom du projet.
     */
    private String nom;

    /**
     * La description du projet.
     */
    private String description;

    /**
     * La mission associée au projet.
     */
    private Mission mission;

    /**
     * La liste des tâches associées au projet.
     */
    private List<Tache> taches;

    /**
     * Constructeur de la classe {@code Projet}.
     *
     * @param id          L'identifiant unique du projet.
     * @param nom         Le nom du projet.
     * @param description La description du projet.
     * @param mission     La mission associée au projet.
     * @param taches      La liste des tâches associées au projet.
     */
    public Projet(int id, String nom, String description, Mission mission, List<Tache> taches) {
        super(id);
        this.nom = nom;
        this.description = description;
        this.mission = mission;
        this.taches = taches;
    }

    /**
     * Obtient le nom du projet.
     *
     * @return Le nom du projet.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du projet.
     *
     * @param nom Le nouveau nom du projet.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la description du projet.
     *
     * @return La description du projet.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du projet.
     *
     * @param description La nouvelle description du projet.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtient la mission associée au projet.
     *
     * @return La mission associée au projet.
     */
    public Mission getMission() {
        return mission;
    }

    /**
     * Définit la mission associée au projet.
     *
     * @param mission La nouvelle mission associée au projet.
     */
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    /**
     * Obtient la liste des tâches associées au projet.
     *
     * @return La liste des tâches.
     */
    public List<Tache> getTaches() {
        return taches;
    }

    /**
     * Définit la liste des tâches associées au projet.
     *
     * @param taches La nouvelle liste des tâches associées au projet.
     */
    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    /**
     * Ajoute une tâche à la liste des tâches associées au projet.
     *
     * @param tache La tâche à ajouter.
     */
    public void ajouterTache(Tache tache) {
        taches.add(tache);
    }

    /**
     * Supprime une tâche de la liste des tâches associées au projet.
     *
     * @param tache La tâche à supprimer.
     */
    public void supprimerTache(Tache tache) {
        taches.remove(tache);
    }

    /**
     * Retourne une représentation textuelle du projet, incluant son
     * identifiant, son nom, sa description, la mission associée et les tâches.
     *
     * @return Une chaîne représentant les informations du projet.
     */
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
