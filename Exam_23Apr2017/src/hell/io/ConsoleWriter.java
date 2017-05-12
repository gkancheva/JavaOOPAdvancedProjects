package hell.io;

/**
 * Created by gery on 23.4.2017 г. at 16:34.
 */
public class ConsoleWriter implements Writer {

    @Override
    public void write(String text) {
        System.out.printf("%s", text);
    }

    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
