package models.Repositories;

import interfaces.Observateur;
import interfaces.Sujet;
import models.Entities.AbstractEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe abstraite représentant un référentiel générique pour gérer les entités
 * d'un type donné.
 * 
 * <p>
 * Cette classe implémente l'interface {@link Sujet} pour permettre
 * l'enregistrement d'observateurs et leur notification en cas de changement
 * d'état. Elle fournit également des opérations de base pour ajouter,
 * modifier, supprimer et récupérer des entités.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 * 
 * @param <T> Le type des entités gérées, qui doit hériter de
 *            {@link AbstractEntity}.
 */
public abstract class AbstractRepository<T extends AbstractEntity> implements Sujet {

    /**
     * Liste des observateurs enregistrés.
     */
    private final List<Observateur> observateurs = new ArrayList<>();

    /**
     * Liste des entités gérées par ce référentiel.
     */
    private final List<T> entites = new ArrayList<>();

    /**
     * Nom du fichier associé à ce référentiel, utilisé pour la persistance.
     */
    public final String nomFichier;

    /**
     * Constructeur de la classe {@code AbstractRepository}.
     * 
     * @param nomFichier Le nom du fichier utilisé pour la persistance des données.
     */
    public AbstractRepository(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    /**
     * Ajoute un observateur à la liste des observateurs enregistrés.
     * 
     * @param observateur L'observateur à ajouter.
     */
    @Override
    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    /**
     * Notifie tous les observateurs enregistrés d'un changement dans l'état des
     * données.
     */
    @Override
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
    public void ajouterTout(List<T> nouvellesEntites) {
        entites.addAll(nouvellesEntites);
        notifierObservateurs();
    }

    /**
     * Ajoute une entité au référentiel.
     * 
     * @param entite L'entité à ajouter.
     */
    public void ajouter(T entite) {
        entites.add(entite);
        notifierObservateurs();
    }

    /**
     * Modifie une entité existante en la remplaçant par une nouvelle version.
     * 
     * @param entite L'entité modifiée à mettre à jour dans le référentiel.
     */
    public void modifier(T entite) {
        entites.removeIf(e -> e.getId() == entite.getId());
        entites.add(entite);
        notifierObservateurs();
    }

    /**
     * Récupère une entité par son identifiant unique.
     * 
     * @param id L'identifiant unique de l'entité.
     * @return Une instance {@link Optional} contenant l'entité, ou vide si
     *         l'entité n'est pas trouvée.
     */
    public Optional<T> getParId(int id) {
        return entites.stream().filter(e -> e.getId() == id).findFirst();
    }

    /**
     * Récupère une copie de la liste de toutes les entités du référentiel.
     * 
     * @return Une liste contenant toutes les entités.
     */
    public List<T> getTout() {
        return new ArrayList<>(entites);
    }

    /**
     * Supprime une entité du référentiel en fonction de son identifiant unique.
     * 
     * @param id L'identifiant unique de l'entité à supprimer.
     */
    public void supprimer(int id) {
        entites.removeIf(e -> e.getId() == id);
        notifierObservateurs();
    }

    /**
     * Récupère le nom du fichier utilisé pour la persistance des données.
     * 
     * @return Le nom du fichier associé à ce référentiel.
     */
    public String getNomFichier() {
        return nomFichier;
    }
}
