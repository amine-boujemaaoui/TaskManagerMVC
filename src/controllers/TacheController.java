package controllers;

import models.Tache;
import models.TacheRepository;
import views.TacheView;

import java.util.Optional;

public class TacheController extends AbstractController<Tache> {
    
    private final TacheView tacheView;

    public TacheController(TacheRepository repository, TacheView tacheView) {
        super(tacheView, repository);
        this.tacheView = tacheView;
    }

    @Override
    public void executer() {
        boolean running = true;

        while (running) {
            String choix = tacheView.afficherMenuEtLireChoix();

            switch (choix) {
                case "1" -> ajouterTache();
                case "2" -> modifierTache();
                case "3" -> supprimerTache();
                case "4" -> afficherTaches();
                case "0" -> running = false;
                default -> tacheView.afficherMessage("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private void ajouterTache() {
        int id = repository.getToutesLesEntites().size() + 1;
        Tache nouvelleTache = tacheView.saisirTache(id);
        repository.ajouterEntite(nouvelleTache);
        tacheView.afficherMessage("Tâche ajoutée avec succès !");
    }

    private void modifierTache() {
        int id = tacheView.demanderIdTache("modifier");
        Optional<Tache> tacheOpt = repository.getEntiteParId(id);

        tacheOpt.ifPresentOrElse(tache -> {
            Tache tacheModifiee = tacheView.modifierTache(tache);
            repository.modifierEntite(tacheModifiee);
            tacheView.afficherMessage("Tâche modifiée avec succès !");
        }, () -> tacheView.afficherMessage("Tâche introuvable."));
    }

    private void supprimerTache() {
        int id = tacheView.demanderIdTache("supprimer");
        if (repository.getEntiteParId(id).isPresent()) {
            repository.supprimerEntite(id);
            tacheView.afficherMessage("Tâche supprimée avec succès !");
        } else {
            tacheView.afficherMessage("Tâche introuvable.");
        }
    }

    private void afficherTaches() {
        tacheView.afficherTaches(repository.getToutesLesEntites());
    }
}
