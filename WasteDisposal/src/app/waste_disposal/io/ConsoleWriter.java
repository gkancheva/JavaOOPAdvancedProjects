package app.waste_disposal.io;

/**
 * Created by gery on 20.4.2017 г. at 11:01.
 */
public class ConsoleWriter implements Writer {
    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
