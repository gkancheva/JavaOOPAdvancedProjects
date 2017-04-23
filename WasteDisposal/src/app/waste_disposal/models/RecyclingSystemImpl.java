package app.waste_disposal.models;

import app.waste_disposal.contracts.RecyclingSystem;

/**
 * Created by gery on 20.4.2017 г. at 12:30.
 */
public class RecyclingSystemImpl implements RecyclingSystem {
    private double energy;
    private double capital;

    public RecyclingSystemImpl() {
        this.energy = 0;
        this.capital = 0;
    }

    @Override
    public void updateData(double capital, double energy) {
        this.energy += energy;
        this.capital += capital;
    }

    @Override
    public String toString() {
        return String.format("Energy: %.2f Capital: %.2f",
                this.energy, this.capital);
    }
}