package models;

import java.util.ArrayList;

public class TacheRepository extends AbstractRepository<Tache> {
    public TacheRepository() {
        super();
    }

    public void notifyObservers() {
        super.notifyObservers(new ArrayList<>(entities));
    }
}
