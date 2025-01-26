package models;

/**
 * Référentiel spécifique pour la gestion des tâches.
 * Étend {@link AbstractRepository} pour fournir des fonctionnalités
 * génériques de gestion des entités et peut inclure des méthodes spécifiques
 * aux tâches.
 */
public class TacheRepository extends AbstractRepository<Tache> {

    public TacheRepository(String nomFichier) {
        super(nomFichier);
    }
}
