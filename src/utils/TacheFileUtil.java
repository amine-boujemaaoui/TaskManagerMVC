package utils;

import java.time.LocalDate;
import models.Entities.Tache;

/**
 * Classe utilitaire pour gérer la persistance des tâches dans un fichier CSV.
 * <p>
 * Cette classe étend {@link AbstractFileUtil} pour fournir des méthodes
 * permettant de
 * convertir des instances de {@link Tache} en format CSV et de recréer ces
 * instances
 * à partir de lignes CSV. Elle est utilisée pour sauvegarder et charger des
 * tâches
 * à partir de fichiers.
 * </p>
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 */
public class TacheFileUtil extends AbstractFileUtil<Tache> {

    /**
     * Constructeur de la classe {@code TacheFileUtil}.
     * <p>
     * Initialise l'utilitaire avec le chemin spécifié pour le fichier CSV.
     * </p>
     *
     * @param cheminFichier Le chemin du fichier CSV utilisé pour stocker les
     *                      tâches.
     */
    public TacheFileUtil(String cheminFichier) {
        super(cheminFichier);
    }

    /**
     * Convertit une tâche en une ligne au format CSV.
     * <p>
     * Chaque champ de la tâche est converti en une chaîne entourée de guillemets.
     * Si une valeur est nulle, elle est représentée par "null" dans le CSV.
     * </p>
     *
     * @param tache La tâche à convertir.
     * @return Une chaîne représentant la tâche au format CSV.
     */
    @Override
    protected String convertirEnCsv(Tache tache) {
        return "\"" + tache.getId() + "\"," +
                "\"" + escapeCsv(tache.getTitre()) + "\"," +
                "\"" + escapeCsv(tache.getDescription()) + "\"," +
                "\"" + (tache.getEcheance() != null ? tache.getEcheance() : "null") + "\"," +
                "\"" + tache.isStatut() + "\"";
    }

    /**
     * Crée une tâche à partir d'une ligne CSV.
     * <p>
     * La ligne CSV est analysée pour extraire les champs de la tâche. Les valeurs
     * extraites
     * sont utilisées pour instancier une nouvelle tâche. Si une date d'échéance est
     * représentée
     * par "null", elle est interprétée comme une valeur {@code null}.
     * </p>
     *
     * @param csvLine La ligne CSV représentant une tâche.
     * @return Une instance de {@link Tache} créée à partir de la ligne CSV.
     * @throws NumberFormatException    Si l'ID ou le statut n'est pas au format
     *                                  attendu.
     * @throws IllegalArgumentException Si le format de la ligne CSV est incorrect.
     */
    @Override
    protected Tache creerDepuisCsv(String csvLine) {
        // Découpe la ligne en respectant les guillemets CSV
        String[] champs = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        int id = Integer.parseInt(champs[0].replaceAll("^\"|\"$", ""));
        String titre = champs[1].replaceAll("^\"|\"$", "");
        String description = champs[2].replaceAll("^\"|\"$", "");
        String echeanceStr = champs[3].replaceAll("^\"|\"$", "");
        LocalDate echeance = "null".equals(echeanceStr) ? null : LocalDate.parse(echeanceStr);
        boolean statut = Boolean.parseBoolean(champs[4].replaceAll("^\"|\"$", ""));

        return new Tache(id, titre, description, echeance, statut);
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
     * @return Le texte échappé, ou une chaîne vide si le texte est {@code null}.
     */
    private String escapeCsv(String texte) {
        if (texte == null) {
            return "";
        }
        return texte.replace("\"", "\"\""); // Doubler les guillemets
    }
}
