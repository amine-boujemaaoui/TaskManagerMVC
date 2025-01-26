package models;

import interfaces.Observateur;
import interfaces.Sujet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends AbstractEntity> implements Sujet {

    private final List<Observateur> observateurs = new ArrayList<>();

    private final List<T> entites = new ArrayList<>();

    public final String nomFichier;

    public AbstractRepository(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    @Override
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    @Override
    public void notifierObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.actualiser();
        }
    }

    public void ajouterTout(List<T> nouvellesEntites) {
        entites.addAll(nouvellesEntites);
        notifierObservateurs();
    }

    public void ajouter(T entite) {
        entites.add(entite);
        notifierObservateurs();
    }

    public void modifier(T entite) {
        entites.removeIf(e -> e.getId() == entite.getId());
        entites.add(entite);
        notifierObservateurs();
    }

    public Optional<T> getParId(int id) {
        return entites.stream().filter(e -> e.getId() == id).findFirst();
    }

    public List<T> getTout() {
        return new ArrayList<>(entites);
    }

    public void supprimer(int id) {
        entites.removeIf(e -> e.getId() == id);
        notifierObservateurs();
    }

    public String getNomFichier() {
        return nomFichier;
    }
}