package interpreters;

import commands.Executable;

import java.lang.reflect.InvocationTargetException;

public interface Interpreter {
    Executable interpretCommand(String input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}