package app.waste_disposal.factories;

import app.waste_disposal.contracts.Waste;
import app.waste_disposal.contracts.WasteFactory;
import app.waste_disposal.models.waste.BaseWaste;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 13:11.
 */
public class WasteFactoryImpl implements WasteFactory {
    private static String WASTE_SUFFIX = "Garbage";

    @Override
    @SuppressWarnings("unchecked")
    public Waste createWaste(String name, double weight, double volumePerKg, String type) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        String baseWasteName = BaseWaste.class.getName();
        String simpleName = BaseWaste.class.getSimpleName();
        baseWasteName = baseWasteName.replace("." + simpleName, "." + type + WASTE_SUFFIX);
        Class<Waste> wasteClass = null;
        try {
            wasteClass = (Class<Waste>) Class.forName(baseWasteName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException();
        }
        Constructor<Waste> constructor = wasteClass.getDeclaredConstructor(
                String.class, double.class, double.class);
        return constructor.newInstance(name, weight, volumePerKg);
    }
}