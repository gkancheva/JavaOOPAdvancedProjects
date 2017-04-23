package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.io.IOManager;

@Alias("ls")
public class TraverseFoldersCommand extends Command {

    @Inject
    private IOManager ioManager;

    public TraverseFoldersCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() {
        String[] data = this.getData();
        if (data.length != 1 && data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        if (data.length == 1) {
            this.ioManager.traverseDirectory(0);
            return;
        }

        this.ioManager.traverseDirectory(Integer.valueOf(data[1]));
    }
}
