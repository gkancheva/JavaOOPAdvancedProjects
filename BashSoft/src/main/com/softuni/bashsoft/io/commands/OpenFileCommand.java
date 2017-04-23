package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.static_data.SessionData;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Alias("open")
public class OpenFileCommand extends Command {

    public OpenFileCommand(String input,String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws IOException {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileName = data[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }
}
