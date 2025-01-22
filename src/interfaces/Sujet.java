package interfaces;

public interface Sujet {
    void ajouterObservateur(Observateur observateur);

    void notifierObservateurs();
}
