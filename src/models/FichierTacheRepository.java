package models;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FichierTacheRepository {

    private final String cheminFichier;

    public FichierTacheRepository(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public void sauvegarder(List<Tache> taches) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            StringBuilder jsonBuilder = new StringBuilder("[\n");
            for (int i = 0; i < taches.size(); i++) {
                Tache tache = taches.get(i);
                jsonBuilder.append("  {")
                        .append("\n    \"titre\": \"").append(tache.getTitre()).append("\",")
                        .append("\n    \"description\": \"").append(tache.getDescription()).append("\",")
                        .append("\n    \"echeance\": \"").append(tache.getEcheance()).append("\",")
                        .append("\n    \"statut\": ").append(tache.isStatut())
                        .append("\n  }");
                if (i < taches.size() - 1) {
                    jsonBuilder.append(",");
                }
                jsonBuilder.append("\n");
            }
            jsonBuilder.append("]");
            writer.write(jsonBuilder.toString());
        }
    }

    public List<Tache> charger() throws IOException {
        List<Tache> taches = new ArrayList<>();
        File fichier = new File(cheminFichier);

        // Si le fichier n'existe pas, retourne une liste vide
        if (!fichier.exists()) {
            return taches;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                jsonBuilder.append(ligne.trim()); // Supprime les espaces inutiles
            }

            String json = jsonBuilder.toString();
            if (json.isEmpty() || json.equals("[]")) {
                return taches; // Retourne une liste vide si le fichier est vide
            }

            // Analyse du JSON formaté
            json = json.substring(1, json.length() - 1); // Supprime les crochets []
            String[] elements = json.split("\\},\\{"); // Divise les objets JSON

            for (String element : elements) {
                element = element.replaceAll("[\\{\\}\\n ]", ""); // Supprime les accolades, sauts de ligne et espaces
                String[] champs = element.split(",");
                String titre = champs[0].split(":")[1].replaceAll("\"", "");
                String description = champs[1].split(":")[1].replaceAll("\"", "");
                String echeanceStr = champs[2].split(":")[1].replaceAll("\"", "");
                LocalDate echeance = "null".equals(echeanceStr) ? null : LocalDate.parse(echeanceStr);
                boolean statut = Boolean.parseBoolean(champs[3].split(":")[1]);

                taches.add(new Tache(titre, description, echeance, statut));
            }
        }

        return taches;
    }

}
