package interpreters;

import annotations.InjectArgs;
import annotations.InjectType;
import commands.Executable;
import core.ManagementSystem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreter implements Interpreter {

    private static final String COMMAND_SUFFIX = "Command";
    private static final String PACKAGE = "commands.";
    private ManagementSystem managementSystem;

    public CommandInterpreter(ManagementSystem managementSystem) {
        this.managementSystem = managementSystem;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Executable interpretCommand(String input) throws
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {
        String[] tokens = input.split("\\|+");
        String command = tokens[0];
        Class<Executable> executableClass = (Class<Executable>) Class.forName(PACKAGE + command + COMMAND_SUFFIX);
        Constructor<Executable> executableConstructor =
                executableClass.getDeclaredConstructor(ManagementSystem.class);
        Executable executable = executableConstructor.newInstance(this.managementSystem);
        this.injectDependencies(executable, tokens);
        return executable;
    }

    private void injectDependencies(Executable executable, String[] tokens) throws IllegalAccessException {
        Field[] fields = executable.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(InjectArgs.class)) {
                field.setAccessible(true);
                field.set(executable, tokens);
                break;
            } else if(field.isAnnotationPresent(InjectType.class)) {
                field.setAccessible(true);
                field.set(executable, tokens[1]);
                break;
            }
        }
    }
}