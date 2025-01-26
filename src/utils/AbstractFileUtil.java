package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFileUtil<T> {

    private final String cheminFichier;

    // Map pour stocker les instances singleton par type concret
    private static final Map<Class<?>, AbstractFileUtil<?>> instances = new HashMap<>();

    protected AbstractFileUtil(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    /**
     * Méthode pour obtenir une instance unique d'une sous-classe spécifique.
     *
     * @param type          La classe de la sous-classe.
     * @param cheminFichier Le chemin du fichier.
     * @param <U>           Le type concret de l'entité.
     * @return L'instance unique de la sous-classe.
     */
    public static <U> AbstractFileUtil<U> getInstance(Class<? extends AbstractFileUtil<U>> type, Object... args) {
        // Vérifie si une instance pour ce type existe déjà
        if (!instances.containsKey(type)) {
            try {
                // Obtenez les types des arguments passés
                Class<?>[] argTypes = new Class<?>[args.length];
                for (int i = 0; i < args.length; i++) {
                    argTypes[i] = args[i].getClass();
                }

                // Crée une nouvelle instance en appelant le constructeur correspondant
                AbstractFileUtil<U> instance = type.getDeclaredConstructor(argTypes).newInstance(args);
                instances.put(type, instance);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la création du singleton pour " + type.getName(), e);
            }
        }

        // Retourne l'instance unique
        @SuppressWarnings("unchecked")
        AbstractFileUtil<U> instance = (AbstractFileUtil<U>) instances.get(type);
        return instance;
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
