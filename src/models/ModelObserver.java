package models;

import java.util.List;

/**
 * Interface pour les observateurs des modèles.
 * Les classes qui implémentent cette interface seront notifiées des changements.
 */
public interface ModelObserver {

    /**
     * Méthode appelée lorsque le modèle subit une mise à jour.
     */
    void update(List<Tache> taches);
}
