package utils;

import models.Tache;

import java.time.LocalDate;

/**
 * Classe utilitaire pour gérer la persistance des tâches dans un fichier CSV.
 * Hérite de {@link AbstractFileUtil} et fournit les méthodes spécifiques
 * pour convertir les tâches en format CSV et les recréer à partir de celui-ci.
 */
public class TacheFileUtil extends AbstractFileUtil<Tache> {

    /**
     * Constructeur de la classe {@code TacheFileUtil}.
     *
     * @param cheminFichier Le chemin du fichier CSV utilisé pour stocker les
     *                      tâches.
     */
    public TacheFileUtil(String cheminFichier) {
        super(cheminFichier);
    }

    /**
     * Convertit une tâche en une ligne au format CSV.
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
     *
     * @param csvLine La ligne CSV représentant une tâche.
     * @return Une instance de {@link Tache} créée à partir de la ligne CSV.
     */
    @Override
    protected Tache creerDepuisCsv(String csvLine) {
        // Découpe la ligne en respectant les guillemets CSV
        String[] champs = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        for (String champ : champs) {
            System.out.println(champ);
        }

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
