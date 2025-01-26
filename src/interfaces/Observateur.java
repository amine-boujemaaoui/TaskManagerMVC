package interfaces;

/**
 * Interface représentant un observateur dans le cadre du patron de conception
 * Observer.
 * 
 * <p>
 * Les classes qui implémentent cette interface doivent définir la logique à
 * exécuter lorsqu'une mise à jour est reçue d'un sujet observable. Ce modèle
 * est utile pour établir une relation de dépendance entre un sujet et ses
 * observateurs, permettant ainsi une notification automatique en cas de
 * changement d'état du sujet.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public interface Observateur {

    /**
     * Méthode appelée pour notifier l'observateur d'une mise à jour.
     * 
     * <p>
     * Cette méthode est invoquée par le sujet observable lorsque son état
     * change. L'implémentation spécifique dépend de la logique métier de la
     * classe qui implémente cette interface.
     * </p>
     */
    void actualiser();
}
