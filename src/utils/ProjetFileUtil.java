package utils;

import models.Entities.Mission;
import models.Entities.Projet;
import models.Entities.Tache;
import models.Repositories.MissionRepository;
import models.Repositories.TacheRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitaire pour gérer la persistance des projets dans un fichier CSV.
 * <p>
 * Cette classe étend {@link AbstractFileUtil} pour fournir des méthodes
 * spécifiques permettant de convertir les objets {@link Projet} en format CSV
 * et de recréer ces objets à partir de lignes CSV. Elle est utilisée pour
 * stocker et charger des projets dans un fichier.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class ProjetFileUtil extends AbstractFileUtil<Projet> {

    private final MissionRepository missionRepository;
    private final TacheRepository tacheRepository;

    /**
     * Constructeur de la classe {@code ProjetFileUtil}.
     * Initialise l'utilitaire avec le chemin spécifié pour le fichier CSV et
     * un référentiel des missions et tâches pour relier les projets à leurs
     * entités.
     *
     * @param cheminFichier     Le chemin du fichier CSV utilisé pour stocker les
     *                          projets.
     * @param missionRepository Le référentiel des missions utilisé pour associer
     *                          une mission à un projet.
     * @param tacheRepository   Le référentiel des tâches utilisé pour associer des
     *                          tâches à un projet.
     */
    public ProjetFileUtil(String cheminFichier, MissionRepository missionRepository, TacheRepository tacheRepository) {
        super(cheminFichier);
        this.missionRepository = missionRepository;
        this.tacheRepository = tacheRepository;
    }

    /**
     * Convertit un projet en une ligne au format CSV.
     * Chaque champ du projet est converti en une chaîne entourée de guillemets.
     * Les identifiants des tâches sont stockés sous forme de liste séparée par des
     * points-virgules.
     *
     * @param projet Le projet à convertir.
     * @return Une chaîne représentant le projet au format CSV.
     */
    @Override
    protected String convertirEnCsv(Projet projet) {
        String missionId = projet.getMission() != null ? String.valueOf(projet.getMission().getId()) : "null";
        String tachesIds = projet.getTaches().stream()
                .map(t -> String.valueOf(t.getId()))
                .collect(Collectors.joining(";"));

        return "\"" + projet.getId() + "\"," +
                "\"" + escapeCsv(projet.getNom()) + "\"," +
                "\"" + escapeCsv(projet.getDescription()) + "\"," +
                "\"" + missionId + "\"," +
                "\"" + tachesIds + "\"";
    }

    /**
     * Crée un projet à partir d'une ligne CSV.
     * Récupère les informations du projet ainsi que ses tâches et sa mission
     * associées.
     *
     * @param csvLine La ligne CSV représentant un projet.
     * @return Une instance de {@link Projet} créée à partir de la ligne CSV.
     * @throws NumberFormatException    Si l'ID ou l'ID de la mission ou des tâches
     *                                  n'est pas un nombre valide.
     * @throws IllegalArgumentException Si le format de la ligne CSV est incorrect.
     */
    @Override
    protected Projet creerDepuisCsv(String csvLine) {
        String[] champs = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        int id = Integer.parseInt(champs[0].replaceAll("^\"|\"$", ""));
        String nom = champs[1].replaceAll("^\"|\"$", "");
        String description = champs[2].replaceAll("^\"|\"$", "");
        String missionIdStr = champs[3].replaceAll("^\"|\"$", "");
        String tachesIdsStr = champs.length > 4 ? champs[4].replaceAll("^\"|\"$", "") : "";

        Mission mission = null;
        if (!"null".equals(missionIdStr)) {
            int missionId = Integer.parseInt(missionIdStr);
            mission = missionRepository.getParId(missionId).orElse(null);
        }

        // Récupération des tâches associées
        List<Tache> taches = tachesIdsStr.isEmpty() ? List.of()
                : List.of(tachesIdsStr.split(";")).stream()
                        .map(idStr -> tacheRepository.getParId(Integer.parseInt(idStr)).orElse(null))
                        .filter(t -> t != null)
                        .collect(Collectors.toList());

        Projet projet = new Projet(id, nom, description, mission, taches);
        return projet;
    }

    /**
     * Échappe les caractères spéciaux pour le format CSV.
     * Cette méthode double les guillemets dans le texte pour éviter les erreurs.
     *
     * @param texte Le texte à échapper.
     * @return Le texte échappé, ou une chaîne vide si le texte est null.
     */
    private String escapeCsv(String texte) {
        if (texte == null)
            return "";
        return texte.replace("\"", "\"\""); // Doubler les guillemets pour éviter les erreurs
    }
}
