package app;

import app.waste_disposal.annotations.Burnable;
import app.waste_disposal.annotations.Recyclable;
import app.waste_disposal.annotations.Storable;
import app.waste_disposal.contracts.*;
import app.waste_disposal.core.Engine;
import app.waste_disposal.factories.StrategyFactoryImpl;
import app.waste_disposal.interpreter.CommandInterpreter;
import app.waste_disposal.io.ConsoleReader;
import app.waste_disposal.io.ConsoleWriter;
import app.waste_disposal.io.Reader;
import app.waste_disposal.io.Writer;
import app.waste_disposal.DefaultGarbageProcessor;
import app.waste_disposal.DefaultStrategyHolder;
import app.waste_disposal.models.RecyclingSystemImpl;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        StrategyHolder holder = new DefaultStrategyHolder();
        try {
            addStrategies(holder);
        } catch (ClassNotFoundException |
                IllegalAccessException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }

        GarbageProcessor processor = new DefaultGarbageProcessor(holder);
        RecyclingSystem system = new RecyclingSystemImpl();

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        Interpreter interpreter = new CommandInterpreter(processor, system);

        Runnable engine = new Engine(reader, writer, interpreter);
        engine.run();
    }

    private static void addStrategies(StrategyHolder holder) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        StrategyFactory factory = new StrategyFactoryImpl();
        holder.addStrategy(Burnable.class,
                factory.createStrategy("Burnable"));
        holder.addStrategy(Recyclable.class,
                factory.createStrategy("Recyclable"));
        holder.addStrategy(Storable.class,
                factory.createStrategy("Storable"));
    }
}
