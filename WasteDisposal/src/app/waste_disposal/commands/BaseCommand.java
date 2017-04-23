package app.waste_disposal.commands;

import app.waste_disposal.contracts.Executable;
import app.waste_disposal.contracts.GarbageProcessor;
import app.waste_disposal.contracts.RecyclingSystem;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 12:53.
 */
public abstract class BaseCommand implements Executable {
    private GarbageProcessor processor;
    private RecyclingSystem recyclingSystem;

    public BaseCommand(GarbageProcessor processor, RecyclingSystem system) {
        this.processor = processor;
        this.recyclingSystem = system;
    }

    @Override
    public abstract String execute() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    protected GarbageProcessor getGarbageProcessor() {
        return this.processor;
    }

    @Override
    public RecyclingSystem getRecyclingSystem() {
        return this.recyclingSystem;
    }
}
