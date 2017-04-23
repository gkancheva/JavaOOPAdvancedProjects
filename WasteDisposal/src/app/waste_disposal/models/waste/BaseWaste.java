package app.waste_disposal.models.waste;

import app.waste_disposal.contracts.Waste;

/**
 * Created by gery on 20.4.2017 г. at 10:29.
 */
public abstract class BaseWaste implements Waste {
    private String name;
    private double volumePerKg;
    private double weight;

    protected BaseWaste(String name, double weight,  double volumePerKg) {
        this.name = name;
        this.weight = weight;
        this.volumePerKg = volumePerKg;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getVolumePerKg() {
        return this.volumePerKg;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}