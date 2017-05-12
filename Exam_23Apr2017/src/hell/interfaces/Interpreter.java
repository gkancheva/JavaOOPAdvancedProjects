package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 23.4.2017 г. at 16:04.
 */
public interface Interpreter {
    Executable interpretCommand(String input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
