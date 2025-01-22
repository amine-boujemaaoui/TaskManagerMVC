package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.AbstractEntity;

public abstract class AbstractRepository<T extends AbstractEntity> extends AbstractModel {

    protected final List<T> entities;

    public AbstractRepository() {
        this.entities = new ArrayList<>();
    }

    public void add(T entity) {
        entities.add(entity);
        notifyObservers(new ArrayList<>(entities));
    }

    public void addAll(List<T> newEntities) {
        entities.addAll(newEntities);
        notifyObservers(new ArrayList<>(entities));
    }

    public void remove(T entity) {
        entities.remove(entity);
        notifyObservers(new ArrayList<>(entities));
    }

    public Optional<T> getById(int id) {
        return entities.stream().filter(e -> e.getId() == id).findFirst();
    }

    public List<T> getAll() {
        return new ArrayList<>(entities);
    }

    public void update(int id, T updatedEntity) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id) {
                entities.set(i, updatedEntity);
                notifyObservers(new ArrayList<>(entities));
                return;
            }
        }
        throw new IllegalArgumentException("Entity with id " + id + " not found.");
    }
}
