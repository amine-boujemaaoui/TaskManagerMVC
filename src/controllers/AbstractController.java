package controllers;

import models.AbstractEntity;
import views.AbstractView;
import models.AbstractModelRepository;

/**
 * Classe abstraite représentant un contrôleur général pour gérer les
 * interactions
 * entre la vue et le modèle dans une architecture MVC.
 *
 * @param <T> Le type des entités gérées par ce contrôleur, qui doit étendre
 *            {@link AbstractEntity}.
 */
public abstract class AbstractController<T extends AbstractEntity> {

    /**
     * La vue associée à ce contrôleur.
     */
    protected AbstractView view;

    /**
     * Le référentiel de données associé à ce contrôleur.
     */
    protected AbstractModelRepository<T> repository;

    /**
     * Constructeur de la classe {@code AbstractController}.
     *
     * @param view       La vue associée à ce contrôleur.
     * @param repository Le référentiel de données associé à ce contrôleur.
     */
    public AbstractController(AbstractView view, AbstractModelRepository<T> repository) {
        this.view = view;
        this.repository = repository;
    }

    /**
     * Obtient le référentiel de données utilisé par ce contrôleur.
     *
     * @return Le référentiel de données.
     */
    public AbstractModelRepository<T> getRepository() {
        return repository;
    }

    /**
     * Méthode abstraite devant être implémentée par les classes dérivées pour
     * exécuter
     * la logique principale du contrôleur.
     */
    public abstract void executer();
}
