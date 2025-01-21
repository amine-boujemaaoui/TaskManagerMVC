package interfaces;

import java.util.List;
import models.AbstractEntity;

public interface Sujet {
    void addObserver(Observateur observer);
    void removeObserver(Observateur observer);
    void notifyObservers(List<? extends AbstractEntity> entities);
}