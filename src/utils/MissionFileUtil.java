package utils;

import models.Mission;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe utilitaire pour gérer la persistance des missions dans un fichier CSV.
 * Hérite de {@link AbstractFileUtil} et fournit les méthodes spécifiques
 * pour convertir les missions en format CSV et les recréer à partir de
 * celui-ci.
 */
public class MissionFileUtil extends AbstractFileUtil<Mission> {

    /**
     * Constructeur de la classe {@code MissionFileUtil}.
     *
     * @param cheminFichier Le chemin du fichier CSV utilisé pour stocker les
     *                      missions.
     */
    public MissionFileUtil(String cheminFichier) {
        super(cheminFichier);
    }

    /**
     * Convertit une mission en une ligne au format CSV.
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
     *
     * @param csvLine La ligne CSV représentant une mission.
     * @return Une instance de {@link Mission} créée à partir de la ligne CSV.
     */
    @Override
    protected Mission creerDepuisCsv(String csvLine) {
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
