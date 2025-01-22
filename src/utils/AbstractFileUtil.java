package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileUtil<T> {
    private final String cheminFichier;

    public AbstractFileUtil(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    /**
     * Sauvegarde la liste des entités dans le fichier.
     *
     * @param entities Liste des entités à sauvegarder.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    public void sauvegarder(List<T> entities) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            StringBuilder jsonBuilder = new StringBuilder("[\n");
            for (int i = 0; i < entities.size(); i++) {
                jsonBuilder.append(convertirEnJson(entities.get(i)));
                if (i < entities.size() - 1) {
                    jsonBuilder.append(",");
                }
                jsonBuilder.append("\n");
            }
            jsonBuilder.append("]");
            writer.write(jsonBuilder.toString());
        }
    }

    /**
     * Charge les entités depuis le fichier.
     *
     * @return Liste des entités chargées.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    public List<T> charger() throws IOException {
        List<T> entities = new ArrayList<>();
        File fichier = new File(cheminFichier);

        if (!fichier.exists()) {
            return entities;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                jsonBuilder.append(ligne.trim());
            }

            String json = jsonBuilder.toString();
            if (json.isEmpty() || json.equals("[]")) {
                return entities;
            }

            json = json.substring(1, json.length() - 1); // Retirer les crochets []
            String[] elements = json.split("},\\{");

            for (String element : elements) {
                element = "{" + element.replaceAll("[\\[\\]]", "") + "}"; // Reformater en JSON valide
                entities.add(creerDepuisJson(element));
            }
        }

        return entities;
    }

    /**
     * Méthode abstraite pour convertir une entité en JSON.
     *
     * @param entity L'entité à convertir.
     * @return La chaîne JSON de l'entité.
     */
    protected abstract String convertirEnJson(T entity);

    /**
     * Méthode abstraite pour créer une entité à partir d'une chaîne JSON.
     *
     * @param json La chaîne JSON de l'entité.
     * @return L'entité créée.
     */
    protected abstract T creerDepuisJson(String json);
}
