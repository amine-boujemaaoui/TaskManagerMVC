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
            StringBuilder jsonBuilder = new StringBuilder("[");
            for (int i = 0; i < taches.size(); i++) {
                Tache tache = taches.get(i);
                jsonBuilder.append("{")
                        .append("\"titre\":\"").append(tache.getTitre()).append("\",")
                        .append("\"description\":\"").append(tache.getDescription()).append("\",")
                        .append("\"echeance\":\"").append(tache.getEcheance()).append("\",")
                        .append("\"statut\":").append(tache.isStatut())
                        .append("}");
                if (i < taches.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");
            writer.write(jsonBuilder.toString());
        }
    }

    public List<Tache> charger() throws IOException {
        List<Tache> taches = new ArrayList<>();
        File fichier = new File(cheminFichier);
        if (!fichier.exists()) {
            return taches; // Fichier non trouvé, retourne une liste vide
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                jsonBuilder.append(ligne);
            }
            String json = jsonBuilder.toString();
            if (json.isEmpty() || json.equals("[]")) {
                return taches; // Pas de données
            }
            // Parse JSON
            json = json.substring(1, json.length() - 1); // Supprime les crochets []
            String[] elements = json.split("\\},\\{");
            for (String element : elements) {
                element = element.replaceAll("[\\{\\}]", ""); // Supprime les accolades
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
