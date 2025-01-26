package models;

/**
 * Référentiel spécifique pour la gestion des missions.
 */
public class MissionRepository extends AbstractRepository<Mission> {

    public MissionRepository(String nomFichier) {
        super(nomFichier);
    }
}
