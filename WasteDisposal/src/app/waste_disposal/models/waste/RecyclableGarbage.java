package app.waste_disposal.models.waste;

import app.waste_disposal.annotations.Recyclable;

/**
 * Created by gery on 20.4.2017 г. at 10:46.
 */
@Recyclable
public class RecyclableGarbage extends BaseWaste{

    public RecyclableGarbage(String name, double weight, double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
