package utils;

import models.Tache;

import java.time.LocalDate;

public class TacheFileUtil extends AbstractFileUtil<Tache> {

    public TacheFileUtil(String cheminFichier) {
        super(cheminFichier);
    }

    @Override
    protected String convertirEnJson(Tache tache) {
        return "  {" +
                "\n    \"id\": " + tache.getId() + "," +
                "\n    \"titre\": \"" + tache.getTitre() + "\"," +
                "\n    \"description\": \"" + tache.getDescription() + "\"," +
                "\n    \"echeance\": \"" + (tache.getEcheance() != null ? tache.getEcheance() : "null") + "\"," +
                "\n    \"statut\": " + tache.isStatut() +
                "\n  }";
    }

    @Override
    protected Tache creerDepuisJson(String json) {
        String[] champs = json.replaceAll("[\\{\\}\\n ]", "").split(",");
        int id = Integer.parseInt(champs[0].split(":")[1]);
        String titre = champs[1].split(":")[1].replaceAll("\"", "");
        String description = champs[2].split(":")[1].replaceAll("\"", "");
        String echeanceStr = champs[3].split(":")[1].replaceAll("\"", "");
        LocalDate echeance = "null".equals(echeanceStr) ? null : LocalDate.parse(echeanceStr);
        boolean statut = Boolean.parseBoolean(champs[4].split(":")[1]);

        return new Tache(id, titre, description, echeance, statut);
    }
}
