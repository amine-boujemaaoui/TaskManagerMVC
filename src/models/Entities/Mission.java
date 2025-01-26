package models.Entities;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe représentant une mission, avec un titre, une date de début, une date
 * de fin, et une liste de noms associés.
 * 
 * <p>
 * Cette classe étend {@link AbstractEntity}, lui conférant un identifiant
 * unique, et ajoute des attributs spécifiques à une mission, tels que le titre,
 * les dates de début et de fin, ainsi qu'une liste de noms.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class Mission extends AbstractEntity {

    /**
     * Le titre de la mission.
     */
    private String titre;

    /**
     * La date de début de la mission.
     */
    private LocalDate dateDebut;

    /**
     * La date de fin de la mission.
     */
    private LocalDate dateFin;

    /**
     * La liste des noms associés à la mission.
     */
    private List<String> noms;

    /**
     * Constructeur de la classe {@code Mission}.
     *
     * @param id        L'identifiant unique de la mission.
     * @param titre     Le titre de la mission.
     * @param dateDebut La date de début de la mission.
     * @param dateFin   La date de fin de la mission.
     * @param noms      La liste des noms associés à la mission.
     */
    public Mission(int id, String titre, LocalDate dateDebut, LocalDate dateFin, List<String> noms) {
        super(id);
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.noms = noms;
    }

    /**
     * Obtient le titre de la mission.
     *
     * @return Le titre de la mission.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit le titre de la mission.
     *
     * @param titre Le nouveau titre de la mission.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Obtient la date de début de la mission.
     *
     * @return La date de début de la mission.
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la date de début de la mission.
     *
     * @param dateDebut La nouvelle date de début de la mission.
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Obtient la date de fin de la mission.
     *
     * @return La date de fin de la mission.
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Définit la date de fin de la mission.
     *
     * @param dateFin La nouvelle date de fin de la mission.
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Obtient la liste des noms associés à la mission.
     *
     * @return La liste des noms associés à la mission.
     */
    public List<String> getNoms() {
        return noms;
    }

    /**
     * Définit la liste des noms associés à la mission.
     *
     * @param noms La nouvelle liste des noms associés.
     */
    public void setNoms(List<String> noms) {
        this.noms = noms;
    }

    /**
     * Retourne une représentation textuelle de la mission, incluant son
     * identifiant, son titre, ses dates de début et de fin, ainsi que la liste des
     * noms associés.
     *
     * @return Une chaîne représentant les informations détaillées de la mission.
     */
    @Override
    public String toString() {
        return "Mission{" +
                "id=" + getId() +
                ", titre='" + titre + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", noms=" + noms +
                '}';
    }
}
