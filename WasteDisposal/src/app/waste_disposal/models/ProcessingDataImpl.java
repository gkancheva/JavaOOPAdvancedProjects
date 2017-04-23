package app.waste_disposal.models;

import app.waste_disposal.contracts.ProcessingData;

/**
 * Created by gery on 20.4.2017 г. at 10:52.
 */
public class ProcessingDataImpl implements ProcessingData {
    private double energyBalance;
    private double capitalBalance;

    public ProcessingDataImpl(double energyBalance, double capitalBalance) {
        this.energyBalance = energyBalance;
        this.capitalBalance = capitalBalance;
    }

    @Override
    public double getEnergyBalance() {
        return this.energyBalance;
    }

    @Override
    public double getCapitalBalance() {
        return this.capitalBalance;
    }
}
