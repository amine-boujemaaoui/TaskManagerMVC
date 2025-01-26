package interfaces;

/**
 * Interface représentant un sujet dans le cadre du patron de conception
 * Observer.
 * Un sujet peut enregistrer des observateurs et les notifier lorsqu'un
 * changement se produit.
 */
public interface Sujet {

    /**
     * Ajoute un observateur à la liste des observateurs du sujet.
     *
     * @param observateur L'observateur à ajouter.
     */
    void ajouterObservateur(Observateur observateur);

    /**
     * Notifie tous les observateurs enregistrés d'un changement dans l'état du
     * sujet.
     */
    void notifierObservateurs();
}