package models.Repositories;

import models.Entities.Projet;

/**
 * Référentiel spécifique pour la gestion des projets.
 * <p>
 * Cette classe hérite de {@link AbstractRepository} et permet de gérer les
 * entités de type {@link Projet}.
 * Elle offre des fonctionnalités telles que l'ajout, la modification, la
 * suppression,
 * et la récupération des projets, ainsi que la notification des observateurs en
 * cas de changements.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class ProjetRepository extends AbstractRepository<Projet> {

    /**
     * Constructeur de la classe {@code ProjetRepository}.
     * <p>
     * Initialise un nouveau référentiel de projets avec un fichier source spécifié.
     * Ce fichier est utilisé pour sauvegarder ou charger les projets persistants.
     * </p>
     *
     * @param nomFichier Le nom du fichier source associé au référentiel.
     *                   Ce fichier contient les données des projets au format CSV.
     */
    public ProjetRepository(String nomFichier) {
        super(nomFichier);
    }
}
