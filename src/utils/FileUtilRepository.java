package utils;

import java.io.IOException;
import java.util.List;

public abstract class FileUtilRepository<T> {
    protected final String cheminFichier;

    public FileUtilRepository(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public abstract void sauvegarder(List<T> entities) throws IOException;

    public abstract List<T> charger() throws IOException;
}
