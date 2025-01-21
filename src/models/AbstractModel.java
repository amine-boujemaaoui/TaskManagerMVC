package models;

import interfaces.Sujet;
import interfaces.Observateur;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel implements Sujet {

    private final List<Observateur> observers;

    public AbstractModel() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observateur observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observateur observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(List<Tache> taches) {
        for (Observateur observer : observers) {
            observer.update(taches);
        }
    }
}
