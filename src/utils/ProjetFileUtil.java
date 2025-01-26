package utils;

import models.Mission;
import models.Projet;
import models.MissionRepository;

/**
 * Classe utilitaire pour gérer la persistance des projets dans un fichier CSV.
 * Hérite de {@link AbstractFileUtil} et fournit les méthodes spécifiques
 * pour convertir les projets en format CSV et les recréer à partir de celui-ci.
 */
public class ProjetFileUtil extends AbstractFileUtil<Projet> {

    private final MissionRepository missionRepository;

    /**
     * Constructeur de la classe {@code ProjetFileUtil}.
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
     *
     * @param csvLine La ligne CSV représentant un projet.
     * @return Une instance de {@link Projet} créée à partir de la ligne CSV.
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
     * Double les guillemets pour les rendre compatibles avec le format CSV.
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
