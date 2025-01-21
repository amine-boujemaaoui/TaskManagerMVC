package interfaces;

import java.util.List;
import models.Tache;

public interface Sujet {
    void addObserver(Observateur observer);
    void removeObserver(Observateur observer);
    void notifyObservers(List<Tache> taches);
}
