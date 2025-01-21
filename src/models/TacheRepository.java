package models;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.FichierTacheRepository;

public class TacheRepository extends AbstractModel {

    private final List<Tache> taches;
    private final FichierTacheRepository fichierRepository;

    public TacheRepository(String cheminFichier) {
        this.taches = new ArrayList<>();
        this.fichierRepository = new FichierTacheRepository(cheminFichier);
        chargerDepuisFichier();
    }

    public void ajouterTache(Tache tache) {
        taches.add(tache);
        sauvegarderDansFichier();
        notifyObservers(new ArrayList<>(taches));
    }

    public void supprimerTache(Tache tache) {
        taches.remove(tache);
        sauvegarderDansFichier();
        notifyObservers(new ArrayList<>(taches));
    }

    public List<Tache> getToutesLesTaches() {
        return new ArrayList<>(taches);
    }

    private void sauvegarderDansFichier() {
        try {
            fichierRepository.sauvegarder(taches);
        } catch (IOException e) {
            e.printStackTrace(); // Gérer les erreurs correctement
        }
    }

    private void chargerDepuisFichier() {
        try {
            taches.addAll(fichierRepository.charger());
        } catch (IOException e) {
            // Fichier absent ou corrompu, démarrage avec une liste vide
            System.err.println("Erreur lors du chargement des tâches : " + e.getMessage());
        }
    }

    public void modifierTache(int index, String nouveauTitre, String nouvelleDescription, LocalDate nouvelleDateEcheance, boolean nouveauStatut) {
        if (index >= 0 && index < taches.size()) {
            Tache tache = taches.get(index);
            tache.setTitre(nouveauTitre);
            tache.setDescription(nouvelleDescription);
            tache.setEcheance(nouvelleDateEcheance); // Mise à jour de la date d'échéance
            tache.setStatut(nouveauStatut); // Mise à jour du statut
            sauvegarderDansFichier();
            notifyObservers(new ArrayList<>(taches)); // Notifie les observateurs (si applicable)
        } else {
            throw new IllegalArgumentException("Index invalide");
        }
    }

}

