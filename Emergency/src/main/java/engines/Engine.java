package engines;

import commands.Executable;
import interpreters.Interpreter;
import io.Reader;
import io.Writer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {

    private static final String END_COMMAND = "EmergencyBreak";
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
            while(!END_COMMAND.equals(input = this.reader.read())) {
                Executable executable = this.interpreter.interpretCommand(input);
                String result = executable.execute();
                this.writer.write(result);
            }
        } catch (IOException |
                ClassNotFoundException |
                IllegalAccessException |
                NoSuchMethodException |
                InvocationTargetException |
                InstantiationException e) {
            e.printStackTrace();
        }
    }
}
