package interfaces;

import java.util.List;
import models.AbstractEntity;

public interface Observateur {
    void update(List<? extends AbstractEntity> entities);
}