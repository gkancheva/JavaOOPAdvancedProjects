package app.waste_disposal.contracts;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 12:22.
 */
public interface Executable {
    String execute() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    RecyclingSystem getRecyclingSystem();
}
