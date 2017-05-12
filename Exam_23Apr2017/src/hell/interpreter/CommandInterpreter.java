package hell.interpreter;

import hell.entities.miscellaneous.Inject;
import hell.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 23.4.2017 г. at 16:04.
 */
public class CommandInterpreter implements Interpreter {
    private static final String PACKAGE_NAME = "hell.commands.";
    private static final String COMMAND_SUFFIX = "Command";

    private Hell hellSystem;

    public CommandInterpreter(Hell hellSystem) {
        this.hellSystem = hellSystem;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Executable interpretCommand(String input) throws ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        String[] commandArgs = input.split("\\s+");
        Class<Executable> executableClass = (Class<Executable>) Class.forName(PACKAGE_NAME + commandArgs[0] + COMMAND_SUFFIX);
        Constructor<Executable> executableConstructor =
                executableClass.getDeclaredConstructor(Hell.class);
        Executable executable = executableConstructor.newInstance(this.hellSystem);
        this.injectDependencies(executable, commandArgs);
        return executable;
    }

    private void injectDependencies(Executable executable, String[] tokens) throws IllegalAccessException {
        Field[] fields = executable.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                field.set(executable, tokens);
            }
        }
    }
}
