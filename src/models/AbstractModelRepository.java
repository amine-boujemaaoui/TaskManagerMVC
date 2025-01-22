package models;

import interfaces.Observateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe abstraite représentant un référentiel générique pour gérer les
 * entités.
 * Fournit des méthodes pour ajouter, modifier, supprimer et notifier les
 * observateurs.
 *
 * @param <T> Le type des entités gérées, qui doit étendre
 *            {@link AbstractEntity}.
 */
public abstract class AbstractModelRepository<T extends AbstractEntity> {

    /**
     * Liste des observateurs à notifier en cas de changement.
     */
    private final List<Observateur> observateurs = new ArrayList<>();

    /**
     * Liste des entités gérées par le référentiel.
     */
    private final List<T> entites = new ArrayList<>();

    /**
     * Ajoute un observateur à la liste des observateurs.
     *
     * @param observateur L'observateur à ajouter.
     */
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    /**
     * Notifie tous les observateurs des changements survenus.
     */
    public void notifierObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.actualiser();
        }
    }

    /**
     * Ajoute une liste d'entités au référentiel.
     *
     * @param nouvellesEntites La liste des nouvelles entités à ajouter.
     */
    public void ajouterToutesLesEntites(List<T> nouvellesEntites) {
        entites.addAll(nouvellesEntites);
        notifierObservateurs();
    }

    /**
     * Ajoute une entité unique au référentiel.
     *
     * @param entite L'entité à ajouter.
     */
    public void ajouterEntite(T entite) {
        entites.add(entite);
        notifierObservateurs();
    }

    /**
     * Modifie une entité existante dans le référentiel.
     * Si une entité avec le même identifiant existe, elle est remplacée.
     *
     * @param entite L'entité à modifier.
     */
    public void modifierEntite(T entite) {
        entites.removeIf(e -> e.getId() == entite.getId());
        entites.add(entite);
        notifierObservateurs();
    }

    /**
     * Recherche une entité par son identifiant.
     *
     * @param id L'identifiant de l'entité recherchée.
     * @return Un {@link Optional} contenant l'entité si elle existe, sinon vide.
     */
    public Optional<T> getEntiteParId(int id) {
        return entites.stream().filter(e -> e.getId() == id).findFirst();
    }

    /**
     * Retourne une copie de la liste de toutes les entités du référentiel.
     *
     * @return La liste de toutes les entités.
     */
    public List<T> getToutesLesEntites() {
        return new ArrayList<>(entites);
    }

    /**
     * Supprime une entité du référentiel en fonction de son identifiant.
     *
     * @param id L'identifiant de l'entité à supprimer.
     */
    public void supprimerEntite(int id) {
        entites.removeIf(e -> e.getId() == id);
        notifierObservateurs();
    }
}
