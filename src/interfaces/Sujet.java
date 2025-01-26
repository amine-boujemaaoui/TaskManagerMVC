package interfaces;

/**
 * Interface représentant un sujet dans le cadre du patron de conception
 * Observer.
 * 
 * <p>
 * Un sujet peut enregistrer des observateurs, les retirer et les notifier
 * lorsqu'un changement dans son état se produit. Ce modèle est couramment
 * utilisé pour établir une relation de dépendance entre un sujet observable et
 * ses observateurs.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public interface Sujet {

    /**
     * Ajoute un observateur à la liste des observateurs du sujet.
     * 
     * <p>
     * Cette méthode permet de lier un observateur au sujet. Une fois enregistré,
     * l'observateur sera notifié à chaque changement d'état du sujet.
     * </p>
     *
     * @param observateur L'observateur à ajouter. Il doit implémenter
     *                    l'interface {@link Observateur}.
     */
    void ajouterObservateur(Observateur observateur);

    /**
     * Notifie tous les observateurs enregistrés d'un changement dans l'état du
     * sujet.
     * 
     * <p>
     * Cette méthode parcourt la liste des observateurs enregistrés et appelle leur
     * méthode {@link Observateur#actualiser()} pour les informer du changement.
     * </p>
     */
    void notifierObservateurs();
}
