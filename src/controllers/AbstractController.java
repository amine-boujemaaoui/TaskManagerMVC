package controllers;

import models.AbstractEntity;
import views.AbstractView;
import models.AbstractRepository;
import utils.AbstractFileUtil;

import java.util.Optional;

public abstract class AbstractController<T extends AbstractEntity> {

    protected AbstractView<T> view;

    protected AbstractRepository<T> repository;

    protected AbstractFileUtil<T> fileUtil;

    public AbstractController(AbstractView<T> view, AbstractRepository<T> repository) {
        this.view = view;
        this.repository = repository;
    }

    public void ajouter() {
        int id = repository.getTout().size() + 1;
        T entity = view.ajouter(id);
        repository.ajouter(entity);
        view.afficherMessage("ASjoutée avec succès !");
    }

    public void modifier() {
        int id = view.demanderId("modifier");
        Optional<T> entiteExistante = repository.getParId(id);

        if (entiteExistante.isPresent()) {
            T entiteModifiee = view.modifier(entiteExistante.get());
            repository.modifier(entiteModifiee);
            view.afficherMessage("Entité modifiée avec succès !");
        } else {
            view.afficherMessage("Entité non trouvée avec l'ID : " + id);
        }
    }

    public void supprimer() {
        int id = view.demanderId("supprimer");
        Optional<T> entiteExistante = repository.getParId(id);

        if (entiteExistante.isPresent()) {
            repository.supprimer(id);
            view.afficherMessage("Entité supprimée avec succès !");
        } else {
            view.afficherMessage("Entité non trouvée avec l'ID : " + id);
        }
    }

    public void afficherTous() {
        var toutesLesEntites = repository.getTout();
        view.afficherTous(toutesLesEntites);
    }

    public void afficherDetails() {
        int id = view.demanderId("");
        Optional<T> entite = repository.getParId(id);

        if (entite.isPresent()) {
            view.afficherDetails(entite.get());
        } else {
            view.afficherMessage("Entité non trouvée avec l'ID : " + id);
        }
    }

    public AbstractRepository<T> getRepository() {
        return repository;
    }

    public void executer() {
        boolean running = true;

        while (running) {
            String choix = view.afficherMenuEtLireChoix();

            switch (choix) {
                case "1" -> ajouter();
                case "2" -> modifier();
                case "3" -> supprimer();
                case "4" -> afficherTous();
                case "5" -> afficherDetails();
                case "0" -> running = false;
                default -> view.afficherMessage("Option invalide. Veuillez réessayer.");
            }
        }

        try {
            fileUtil.sauvegarder(repository.getTout());
            view.afficherMessage("Données sauvegardées avec succès !");
        } catch (Exception e) {
            view.afficherMessage("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
}
