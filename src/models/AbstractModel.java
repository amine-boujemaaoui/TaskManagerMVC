package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite définissant la base pour tous les modèles de l'application.
 * Permet aux modèles d'informer les observateurs (comme les vues) des
 * changements.
 */
public abstract class AbstractModel {

    private final List<ModelObserver> observers;

    public AbstractModel() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(List<Tache> taches) {
        for (ModelObserver observer : observers) {
            observer.update(taches);
        }
    }
}
