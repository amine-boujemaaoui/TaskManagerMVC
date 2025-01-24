package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite pour gérer la persistance des entités dans un fichier au
 * format CSV.
 * Fournit des méthodes génériques pour sauvegarder et charger les entités.
 *
 * @param <T> Le type des entités gérées par cette classe.
 */
public abstract class AbstractFileUtil<T> {

    /**
     * Le chemin du fichier utilisé pour la persistance.
     */
    private final String cheminFichier;

    /**
     * Constructeur de la classe {@code AbstractFileUtil}.
     *
     * @param cheminFichier Le chemin du fichier où les entités seront sauvegardées
     *                      ou chargées.
     */
    public AbstractFileUtil(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    /**
     * Sauvegarde la liste des entités dans le fichier au format CSV.
     *
     * @param entities Liste des entités à sauvegarder.
     * @throws IOException En cas d'erreur lors de l'écriture dans le fichier.
     */
    public void sauvegarder(List<T> entities) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (T entity : entities) {
                writer.write(convertirEnCsv(entity));
                writer.newLine();
            }
        }
    }

    /**
     * Charge les entités depuis le fichier CSV.
     *
     * @return Liste des entités chargées depuis le fichier.
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    public List<T> charger() throws IOException {
        List<T> entities = new ArrayList<>();
        File fichier = new File(cheminFichier);

        if (!fichier.exists()) {
            return entities;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                entities.add(creerDepuisCsv(ligne));
            }
        }

        return entities;
    }

    /**
     * Méthode abstraite pour convertir une entité en une ligne CSV.
     *
     * @param entity L'entité à convertir.
     * @return La représentation CSV de l'entité.
     */
    protected abstract String convertirEnCsv(T entity);

    /**
     * Méthode abstraite pour créer une entité à partir d'une ligne CSV.
     *
     * @param csvLine La ligne CSV représentant l'entité.
     * @return L'entité créée à partir de la ligne CSV.
     */
    protected abstract T creerDepuisCsv(String csvLine);
}
