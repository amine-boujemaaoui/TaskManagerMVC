package interfaces;

/**
 * Interface représentant un observateur dans le cadre du patron de conception
 * Observer.
 * Les classes qui implémentent cette interface doivent définir la logique à
 * exécuter
 * lorsqu'une mise à jour est reçue.
 */
public interface Observateur {

    /**
     * Méthode appelée pour notifier l'observateur d'une mise à jour.
     */
    void actualiser();
}
