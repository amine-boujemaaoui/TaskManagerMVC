package models.Repositories;

import models.Entities.Tache;

/**
 * Référentiel spécifique pour la gestion des tâches.
 * <p>
 * Cette classe hérite de {@link AbstractRepository} et gère les entités de type
 * {@link Tache}.
 * Elle permet d'effectuer des opérations CRUD (ajout, modification,
 * suppression, récupération)
 * sur les tâches, et de notifier les observateurs en cas de modifications.
 * </p>
 * <p>
 * Le référentiel utilise un fichier source spécifié pour la persistance des
 * données des tâches.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class TacheRepository extends AbstractRepository<Tache> {

    /**
     * Constructeur de la classe {@code TacheRepository}.
     * <p>
     * Initialise un nouveau référentiel de tâches avec le fichier source spécifié.
     * Ce fichier est utilisé pour sauvegarder et charger les données persistantes
     * des tâches.
     * </p>
     *
     * @param nomFichier Le nom du fichier source associé au référentiel.
     *                   Ce fichier contient les données des tâches au format CSV.
     */
    public TacheRepository(String nomFichier) {
        super(nomFichier);
    }
}
