package app.waste_disposal.models.strategies;

import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;

/**
 * Created by gery on 20.4.2017 г. at 10:49.
 */
public class BurnableStrategy extends BaseGarbageStrategy {

    @Override
    public ProcessingData processGarbage(Waste garbage) {
        double totalGarbageVolume = super.getTotalGarbageVolume(garbage);
        super.setEnergyProduced(totalGarbageVolume);
        super.setEnergyUsed(totalGarbageVolume * 0.2);
        return super.getProcessingData();
    }
}