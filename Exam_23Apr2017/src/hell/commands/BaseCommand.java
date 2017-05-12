package hell.commands;

import hell.interfaces.*;

/**
 * Created by gery on 24.4.2017 г. at 10:06.
 */
public abstract class BaseCommand implements Executable {
    private Hell hellSystem;

    protected BaseCommand(Hell hellSystem) {
        this.hellSystem = hellSystem;
    }

    @Override
    public abstract String execute();

    protected Hell getHellSystem() {
        return this.hellSystem;
    }
}
