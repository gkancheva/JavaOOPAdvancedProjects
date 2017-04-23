package app.waste_disposal.commands;

import app.waste_disposal.annotations.InjectArgs;
import app.waste_disposal.contracts.*;
import app.waste_disposal.factories.WasteFactoryImpl;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 12:26.
 */
public class ProcessGarbage extends BaseCommand {
    public static final String SUCCESSFULLY_PROCESSED_WASTE = "%.2f kg of %s successfully processed!";
    private WasteFactory factory;

    @InjectArgs
    private String[] params;

    public ProcessGarbage(GarbageProcessor processor, RecyclingSystem system) {
        super(processor, system);
        this.factory = new WasteFactoryImpl();
    }

    @Override
    public String execute() throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        String name = this.params[0];
        double weight = Double.parseDouble(this.params[1]);
        double volumePerKg = Double.parseDouble(this.params[2]);
        String type = params[3];
        Waste waste = this.factory.createWaste(name, weight, volumePerKg, type);
        ProcessingData result = super.getGarbageProcessor().processWaste(waste);
        super.getRecyclingSystem().updateData(result.getCapitalBalance(), result.getEnergyBalance());
        return String.format(SUCCESSFULLY_PROCESSED_WASTE, weight, name);
    }
}