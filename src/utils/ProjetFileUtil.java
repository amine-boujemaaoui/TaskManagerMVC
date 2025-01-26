package utils;

import models.Entities.Mission;
import models.Entities.Projet;
import models.Repositories.MissionRepository;

/**
 * Classe utilitaire pour gérer la persistance des projets dans un fichier CSV.
 * <p>
 * Cette classe étend {@link AbstractFileUtil} pour fournir des méthodes
 * spécifiques
 * permettant de convertir les objets {@link Projet} en format CSV et de recréer
 * ces objets
 * à partir de lignes CSV. Elle est utilisée pour stocker et charger des projets
 * dans un fichier.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class ProjetFileUtil extends AbstractFileUtil<Projet> {

    /**
     * Le référentiel des missions, utilisé pour associer une mission existante à un
     * projet
     * lors du chargement depuis un fichier CSV.
     */
    private final MissionRepository missionRepository;

    /**
     * Constructeur de la classe {@code ProjetFileUtil}.
     * <p>
     * Initialise l'utilitaire avec le chemin spécifié pour le fichier CSV et un
     * référentiel
     * des missions qui permettra de lier des projets à des missions existantes.
     * </p>
     *
     * @param cheminFichier     Le chemin du fichier CSV utilisé pour stocker les
     *                          projets.
     * @param missionRepository Le référentiel des missions utilisé pour associer
     *                          une mission à un projet.
     */
    public ProjetFileUtil(String cheminFichier, MissionRepository missionRepository) {
        super(cheminFichier);
        this.missionRepository = missionRepository;
    }

    /**
     * Convertit un projet en une ligne au format CSV.
     * <p>
     * Chaque champ du projet est converti en une chaîne entourée de guillemets.
     * Si une mission est associée au projet, son identifiant est inclus, sinon
     * "null" est utilisé.
     * </p>
     *
     * @param projet Le projet à convertir.
     * @return Une chaîne représentant le projet au format CSV.
     */
    @Override
    protected String convertirEnCsv(Projet projet) {
        String missionId = projet.getMission() != null ? String.valueOf(projet.getMission().getId()) : "null";
        return "\"" + projet.getId() + "\"," +
                "\"" + escapeCsv(projet.getNom()) + "\"," +
                "\"" + escapeCsv(projet.getDescription()) + "\"," +
                "\"" + missionId + "\"";
    }

    /**
     * Crée un projet à partir d'une ligne CSV.
     * <p>
     * La ligne CSV est analysée pour extraire les champs d'un projet. Si un
     * identifiant
     * de mission est présent, il est utilisé pour récupérer la mission
     * correspondante
     * depuis le référentiel des missions.
     * </p>
     *
     * @param csvLine La ligne CSV représentant un projet.
     * @return Une instance de {@link Projet} créée à partir de la ligne CSV.
     * @throws NumberFormatException    Si l'ID ou l'ID de la mission n'est pas un
     *                                  nombre valide.
     * @throws IllegalArgumentException Si le format de la ligne CSV est incorrect.
     */
    @Override
    protected Projet creerDepuisCsv(String csvLine) {
        // Découpe la ligne en respectant les guillemets CSV
        String[] champs = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        int id = Integer.parseInt(champs[0].replaceAll("^\"|\"$", ""));
        String nom = champs[1].replaceAll("^\"|\"$", "");
        String description = champs[2].replaceAll("^\"|\"$", "");
        String missionIdStr = champs[3].replaceAll("^\"|\"$", "");

        Mission mission = null;
        if (!"null".equals(missionIdStr)) {
            int missionId = Integer.parseInt(missionIdStr);
            mission = missionRepository.getParId(missionId).orElse(null);
        }

        return new Projet(id, nom, description, mission, null);
    }

    /**
     * Échappe les caractères spéciaux pour le format CSV.
     * <p>
     * Cette méthode double les guillemets dans le texte pour s'assurer qu'ils sont
     * correctement
     * interprétés dans le format CSV. Si le texte est {@code null}, une chaîne vide
     * est retournée.
     * </p>
     *
     * @param texte Le texte à échapper.
     * @return Le texte échappé, ou une chaîne vide si le texte est null.
     */
    private String escapeCsv(String texte) {
        if (texte == null)
            return "";
        return texte.replace("\"", "\"\""); // Doubler les guillemets
    }
}
