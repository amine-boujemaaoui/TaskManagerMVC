package models.Repositories;

import models.Entities.Mission;

/**
 * Référentiel spécifique pour la gestion des entités de type {@link Mission}.
 * <p>
 * Cette classe hérite de {@link AbstractRepository} et utilise la classe
 * {@link Mission} comme type d'entité.
 * Elle permet d'effectuer des opérations standard sur les missions, telles que
 * l'ajout, la suppression, la modification, et la récupération des missions.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class MissionRepository extends AbstractRepository<Mission> {

    /**
     * Constructeur de la classe {@code MissionRepository}.
     *
     * @param nomFichier Le nom du fichier associé à ce référentiel, utilisé pour la
     *                   persistance des missions.
     */
    public MissionRepository(String nomFichier) {
        super(nomFichier);
    }
}
