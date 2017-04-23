package app.waste_disposal.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gery on 20.4.2017 г. at 11:00.
 */
public class ConsoleReader implements Reader{
    private BufferedReader reader;

    public ConsoleReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String read() throws IOException {
        return this.reader.readLine();
    }
}
