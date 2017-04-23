package app.waste_disposal.interpreter;

import app.waste_disposal.annotations.InjectArgs;
import app.waste_disposal.contracts.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 12:23.
 */
public class CommandInterpreter implements Interpreter {
    private static final String PACKAGE_NAME = "app.waste_disposal.commands.";

    private GarbageProcessor garbageProcessor;
    private RecyclingSystem system;

    public CommandInterpreter(GarbageProcessor garbageProcessor, RecyclingSystem system) {
        this.garbageProcessor = garbageProcessor;
        this.system = system;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Executable interpretCommand(String input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String[] commandArgs = input.split("\\s+");
        Class<Executable> executableClass = (Class<Executable>) Class.forName(PACKAGE_NAME + commandArgs[0]);
        Constructor<Executable> executableConstructor = executableClass.getDeclaredConstructor(
                GarbageProcessor.class,
                RecyclingSystem.class);
        Executable executable = executableConstructor.newInstance(this.garbageProcessor, this.system);
        if(commandArgs.length > 1) {
            this.injectDependencies(executable, commandArgs[1].split("\\|+"));
        }
        return executable;
    }

    private void injectDependencies(Executable executable, String[] tokens) throws IllegalAccessException {
        Field[] fields = executable.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(InjectArgs.class)) {
                field.setAccessible(true);
                field.set(executable, tokens);
            }
        }
    }
}