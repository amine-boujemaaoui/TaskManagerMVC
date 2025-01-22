package controllers;

import models.AbstractEntity;
import views.AbstractView;
import models.AbstractModelRepository;

public abstract class AbstractController<T extends AbstractEntity> {
    
    protected AbstractView view;
    protected AbstractModelRepository<T> repository;

    public AbstractController(AbstractView view, AbstractModelRepository<T> repository) {
        this.view = view;
        this.repository = repository;
    }

    public AbstractModelRepository<T> getRepository() {
        return repository;
    }

    public abstract void executer();
}
