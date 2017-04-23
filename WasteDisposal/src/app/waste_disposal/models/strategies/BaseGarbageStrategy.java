package app.waste_disposal.models.strategies;

import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.ProcessingData;
import app.waste_disposal.contracts.Waste;
import app.waste_disposal.models.ProcessingDataImpl;

/**
 * Created by gery on 20.4.2017 г. at 10:48.
 */
public abstract class BaseGarbageStrategy implements GarbageDisposalStrategy{
    private double energyProduced;
    private double energyUsed;
    private double capitalEarned;
    private double capitalUsed;
    private ProcessingData processingData;

    protected BaseGarbageStrategy() {
    }

    @Override
    public abstract ProcessingData processGarbage(Waste garbage);

    protected void setEnergyProduced(double energyProduced) {
        this.energyProduced = energyProduced;
    }

    protected void setEnergyUsed(double energyUsed) {
        this.energyUsed = energyUsed;
    }

    protected void setCapitalEarned(double capitalEarned) {
        this.capitalEarned = capitalEarned;
    }

    protected void setCapitalUsed(double capitalUsed) {
        this.capitalUsed = capitalUsed;
    }

    protected void setProcessingData() {
        this.processingData = new ProcessingDataImpl(this.getEnergyBalance(), this.getCapitalBalance());
    }

    protected ProcessingData getProcessingData() {
        this.setProcessingData();
        return this.processingData;
    }

    protected double getTotalGarbageVolume(Waste waste) {
        return waste.getWeight() * waste.getVolumePerKg();
    }

    private double getEnergyBalance() {
        return this.energyProduced - this.energyUsed;
    }

    private double getCapitalBalance() {
        return this.capitalEarned - this.capitalUsed;
    }

}
