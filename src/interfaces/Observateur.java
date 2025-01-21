package interfaces;

import java.util.List;
import models.Tache;

public interface Observateur {
    void update(List<Tache> taches);
}
