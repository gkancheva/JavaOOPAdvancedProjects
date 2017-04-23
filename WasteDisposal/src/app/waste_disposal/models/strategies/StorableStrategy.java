package app.waste_disposal.models.strategies;

import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;

/**
 * Created by gery on 20.4.2017 г. at 10:50.
 */
public class StorableStrategy extends BaseGarbageStrategy {
    @Override
    public ProcessingData processGarbage(Waste garbage) {
        double totalGarbageVolume = super.getTotalGarbageVolume(garbage);
        super.setEnergyUsed(totalGarbageVolume * 0.13);
        super.setCapitalUsed(totalGarbageVolume * 0.65);
        return super.getProcessingData();
    }
}
