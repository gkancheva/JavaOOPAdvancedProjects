package hell.core;

import hell.interfaces.Executable;
import hell.interfaces.Hell;
import hell.interfaces.Interpreter;
import hell.io.Reader;
import hell.io.Writer;

/**
 * Created by gery on 23.4.2017 г. at 16:06.
 */
public class Engine implements Runnable {
    private static final String END_COMMAND = "Quit";

    private Reader reader;
    private Writer writer;
    private Hell hell;
    private Interpreter interpreter;

    public Engine(Reader reader, Writer writer, Hell hell, Interpreter interpreter) {
        this.reader = reader;
        this.writer = writer;
        this.hell = hell;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        try {
            String input;
            while(!END_COMMAND.equals(input = reader.read())) {
                Executable executable = interpreter.interpretCommand(input);
                String output = executable.execute();
                this.writer.writeLine(output);
            }
            this.writer.writeLine(this.hell.toString());
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
