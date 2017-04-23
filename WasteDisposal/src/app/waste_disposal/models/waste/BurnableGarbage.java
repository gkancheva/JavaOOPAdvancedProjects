package app.waste_disposal.models.waste;

import app.waste_disposal.annotations.Burnable;

/**
 * Created by gery on 20.4.2017 г. at 10:47.
 */
@Burnable
public class BurnableGarbage extends BaseWaste {

    public BurnableGarbage(String name, double weight, double volumePerKg) {
        super(name, weight, volumePerKg);
    }
}
