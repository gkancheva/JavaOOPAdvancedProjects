package app.waste_disposal.factories;

import app.waste_disposal.contracts.GarbageDisposalStrategy;
import app.waste_disposal.contracts.StrategyFactory;
import app.waste_disposal.models.strategies.BaseGarbageStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by gery on 21.4.2017 г. at 13:41.
 */
public class StrategyFactoryImpl implements StrategyFactory{

    private static String WASTE_SUFFIX = "Strategy";

    @Override
    @SuppressWarnings("unchecked")
    public GarbageDisposalStrategy createStrategy(String type) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        String baseStrategyName = BaseGarbageStrategy.class.getName();
        String simpleName = BaseGarbageStrategy.class.getSimpleName();
        baseStrategyName = baseStrategyName.replace("." + simpleName, "." + type + WASTE_SUFFIX);
        Class<GarbageDisposalStrategy> strategyClass = (Class<GarbageDisposalStrategy>) Class.forName(baseStrategyName);
        Constructor<GarbageDisposalStrategy> constructor = strategyClass.getDeclaredConstructor();
        return constructor.newInstance();
    }
}