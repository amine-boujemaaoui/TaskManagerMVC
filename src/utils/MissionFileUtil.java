package utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import models.Entities.Mission;

/**
 * Classe utilitaire pour gérer la persistance des missions dans un fichier CSV.
 * <p>
 * Cette classe étend {@link AbstractFileUtil} pour fournir des méthodes
 * spécifiques
 * permettant de convertir les objets {@link Mission} en format CSV et de
 * recréer ces objets
 * à partir de lignes CSV. Elle est utilisée pour stocker et charger des
 * missions dans un fichier.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class MissionFileUtil extends AbstractFileUtil<Mission> {

    /**
     * Constructeur de la classe {@code MissionFileUtil}.
     * <p>
     * Initialise l'utilitaire avec le chemin spécifié pour le fichier CSV qui sera
     * utilisé
     * pour stocker ou charger les missions.
     * </p>
     *
     * @param cheminFichier Le chemin du fichier CSV utilisé pour stocker les
     *                      missions.
     */
    public MissionFileUtil(String cheminFichier) {
        super(cheminFichier);
    }

    /**
     * Convertit une mission en une ligne au format CSV.
     * <p>
     * Chaque champ de la mission est converti en une chaîne entourée de guillemets,
     * et les valeurs nulles sont remplacées par "null". Les noms associés à la
     * mission
     * sont séparés par des points-virgules.
     * </p>
     *
     * @param mission La mission à convertir.
     * @return Une chaîne représentant la mission au format CSV.
     */
    @Override
    protected String convertirEnCsv(Mission mission) {
        return "\"" + mission.getId() + "\"," +
                "\"" + escapeCsv(mission.getTitre()) + "\"," +
                "\"" + (mission.getDateDebut() != null ? mission.getDateDebut() : "null") + "\"," +
                "\"" + (mission.getDateFin() != null ? mission.getDateFin() : "null") + "\"," +
                "\"" + escapeCsv(String.join(";", mission.getNoms())) + "\"";
    }

    /**
     * Crée une mission à partir d'une ligne CSV.
     * <p>
     * La ligne CSV est analysée pour extraire les champs de la mission. Les valeurs
     * sont
     * nettoyées des guillemets et les dates sont converties en objets
     * {@link LocalDate}.
     * Les noms associés sont séparés par des points-virgules pour recréer la liste.
     * </p>
     *
     * @param csvLine La ligne CSV représentant une mission.
     * @return Une instance de {@link Mission} créée à partir de la ligne CSV.
     * @throws NumberFormatException    Si l'ID n'est pas un nombre valide.
     * @throws IllegalArgumentException Si le format de la ligne CSV est incorrect.
     */
    @Override
    protected Mission creerDepuisCsv(String csvLine) {
        // Découpe la ligne en respectant les guillemets CSV
        String[] champs = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        int id = Integer.parseInt(champs[0].replaceAll("^\"|\"$", ""));
        String titre = champs[1].replaceAll("^\"|\"$", "");
        String dateDebutStr = champs[2].replaceAll("^\"|\"$", "");
        LocalDate dateDebut = "null".equals(dateDebutStr) ? null : LocalDate.parse(dateDebutStr);
        String dateFinStr = champs[3].replaceAll("^\"|\"$", "");
        LocalDate dateFin = "null".equals(dateFinStr) ? null : LocalDate.parse(dateFinStr);
        String nomsStr = champs[4].replaceAll("^\"|\"$", "");
        List<String> noms = Arrays.stream(nomsStr.split(";")).collect(Collectors.toList());

        return new Mission(id, titre, dateDebut, dateFin, noms);
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
