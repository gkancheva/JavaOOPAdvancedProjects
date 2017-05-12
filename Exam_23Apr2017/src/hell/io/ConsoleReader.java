package hell.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gery on 23.4.2017 г. at 16:33.
 */
public class ConsoleReader implements Reader {

    private BufferedReader reader;

    public ConsoleReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String read() throws IOException {
        return this.reader.readLine();
    }
}
