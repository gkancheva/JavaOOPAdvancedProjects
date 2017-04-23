package app.waste_disposal.contracts;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 12:22.
 */
public interface Interpreter {
    Executable interpretCommand(String input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}