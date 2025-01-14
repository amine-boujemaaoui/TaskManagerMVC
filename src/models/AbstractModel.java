package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite définissant la base pour tous les modèles de l'application.
 * Permet aux modèles d'informer les observateurs (comme les vues) des changements.
 */
public abstract class AbstractModel {

    /**
     * Liste des observateurs associés au modèle.
     * Les observateurs sont généralement des vues qui doivent être mises à jour.
     */
    private final List<ModelObserver> observers;

    /**
     * Constructeur par défaut qui initialise la liste des observateurs.
     */
    public AbstractModel() {
        this.observers = new ArrayList<>();
    }

    /**
     * Ajoute un observateur au modèle.
     *
     * @param observer l'observateur à ajouter.
     */
    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    /**
     * Supprime un observateur du modèle.
     *
     * @param observer l'observateur à supprimer.
     */
    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifie tous les observateurs des changements dans le modèle.
     */
    protected void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.update();
        }
    }
}
