package app.waste_disposal.contracts;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 21.4.2017 г. at 13:41.
 */
public interface StrategyFactory {
    GarbageDisposalStrategy createStrategy(String type) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}