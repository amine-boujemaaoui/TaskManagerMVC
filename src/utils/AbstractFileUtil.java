package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe utilitaire abstraite pour la gestion des fichiers au format CSV.
 * <p>
 * Cette classe permet de sauvegarder et de charger des entités de type
 * générique {@code T}
 * depuis un fichier CSV. Elle implémente un mécanisme de singleton pour
 * garantir une
 * instance unique par type de sous-classe.
 * 
 * @author Le Mouel, Boujemaaoui, Laouaj
 *
 * @param <T> Le type des entités gérées par cette classe.
 */
public abstract class AbstractFileUtil<T> {

    /**
     * Chemin du fichier utilisé pour stocker les données.
     */
    private final String cheminFichier;

    /**
     * Map pour stocker les instances singleton par type concret.
     */
    private static final Map<Class<?>, AbstractFileUtil<?>> instances = new HashMap<>();

    /**
     * Constructeur protégé pour initialiser le fichier source.
     *
     * @param cheminFichier Le chemin du fichier source.
     */
    protected AbstractFileUtil(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    /**
     * Méthode pour obtenir une instance unique d'une sous-classe spécifique.
     * <p>
     * Utilise un constructeur de la sous-classe, avec les arguments spécifiés,
     * pour créer une instance si elle n'existe pas encore. Cette instance est
     * ensuite
     * stockée pour être réutilisée lors des appels suivants.
     * </p>
     *
     * @param type La classe de la sous-classe à instancier.
     * @param args Les arguments à passer au constructeur de la sous-classe.
     * @param <U>  Le type concret des entités gérées.
     * @return L'instance unique de la sous-classe.
     * @throws RuntimeException Si une erreur survient lors de la création de
     *                          l'instance.
     */
    public static <U> AbstractFileUtil<U> getInstance(Class<? extends AbstractFileUtil<U>> type, Object... args) {
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

        @SuppressWarnings("unchecked")
        AbstractFileUtil<U> instance = (AbstractFileUtil<U>) instances.get(type);
        return instance;
    }

    /**
     * Sauvegarde une liste d'entités dans le fichier au format CSV.
     *
     * @param entities La liste des entités à sauvegarder.
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
     * @return Une liste des entités chargées depuis le fichier.
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
     * @return La chaîne représentant l'entité au format CSV.
     */
    protected abstract String convertirEnCsv(T entity);

    /**
     * Méthode abstraite pour créer une entité à partir d'une ligne CSV.
     *
     * @param csvLine La ligne CSV représentant une entité.
     * @return Une instance de l'entité créée à partir de la ligne CSV.
     */
    protected abstract T creerDepuisCsv(String csvLine);
}
