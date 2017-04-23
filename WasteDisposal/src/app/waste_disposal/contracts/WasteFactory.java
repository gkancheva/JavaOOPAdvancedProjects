package app.waste_disposal.contracts;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 13:11.
 */
public interface WasteFactory {
    Waste createWaste(String name, double weight, double volumePerKg, String type) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException;
}
