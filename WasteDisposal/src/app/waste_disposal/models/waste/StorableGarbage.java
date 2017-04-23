package app.waste_disposal.models.waste;

import app.waste_disposal.annotations.Storable;

/**
 * Created by gery on 20.4.2017 г. at 10:47.
 */
@Storable
public class StorableGarbage extends BaseWaste {

    public StorableGarbage(String name, double weight, double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
