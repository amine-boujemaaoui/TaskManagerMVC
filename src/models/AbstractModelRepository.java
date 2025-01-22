package models;

import interfaces.Observateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractModelRepository<T extends AbstractEntity> {

    private final List<Observateur> observateurs = new ArrayList<>();
    private final List<T> entites = new ArrayList<>();

    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    public void notifierObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.actualiser();
        }
    }

    public void ajouterToutesLesEntites(List<T> nouvellesEntites) {
        entites.addAll(nouvellesEntites);
        notifierObservateurs();
    }

    public void ajouterEntite(T entite) {
        entites.add(entite);
        notifierObservateurs();
    }

    public void modifierEntite(T entite) {
        entites.removeIf(e -> e.getId() == entite.getId());
        entites.add(entite);
        notifierObservateurs();
    }

    public Optional<T> getEntiteParId(int id) {
        return entites.stream().filter(e -> e.getId() == id).findFirst();
    }

    public List<T> getToutesLesEntites() {
        return new ArrayList<>(entites);
    }

    public void supprimerEntite(int id) {
        entites.removeIf(e -> e.getId() == id);
        notifierObservateurs();
    }
}
