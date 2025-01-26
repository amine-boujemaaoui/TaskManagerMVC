package models;

/**
 * Référentiel spécifique pour la gestion des projets.
 */
public class ProjetRepository extends AbstractRepository<Projet> {

    public ProjetRepository(String nomFichier) {
        super(nomFichier);
    }
}
