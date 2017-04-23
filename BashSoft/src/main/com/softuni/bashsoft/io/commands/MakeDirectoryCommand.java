package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.io.IOManager;

@Alias("mkdir")
public class MakeDirectoryCommand extends Command {

    @Inject
    private IOManager ioManager;

    public MakeDirectoryCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }
        String folderName = data[1];
        this.ioManager.createDirectoryInCurrentFolder(folderName);
    }
}
