package app.waste_disposal.core;

import app.waste_disposal.contracts.Executable;
import app.waste_disposal.contracts.Interpreter;
import app.waste_disposal.io.Reader;
import app.waste_disposal.io.Writer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by gery on 20.4.2017 г. at 12:07.
 */
public class Engine implements Runnable {
    private static final String END_COMMAND = "TimeToRecycle";

    private Reader reader;
    private Writer writer;
    private Interpreter interpreter;

    public Engine(Reader reader, Writer writer, Interpreter interpreter) {
        this.reader = reader;
        this.writer = writer;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        try {
            String input;
            while(!END_COMMAND.equals(input = reader.read())) {
                Executable executable = interpreter.interpretCommand(input);
                String output = executable.execute();
                this.writer.write(output);
            }
        } catch (IOException |
                ClassNotFoundException |
                IllegalAccessException |
                NoSuchMethodException |
                InvocationTargetException |
                InstantiationException e){
            e.printStackTrace();
        }
    }
}
