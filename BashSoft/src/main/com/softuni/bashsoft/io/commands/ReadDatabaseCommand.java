package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.repository.StudentRepository;

import java.io.IOException;

@Alias("readdb")
public class ReadDatabaseCommand extends Command {

    @Inject
    private StudentRepository repository;

    public ReadDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() throws IOException {
        String[] data = this.getData();
        if (data.length != 2) {
            throw new InvalidInputException(this.getInput());
        }
        String fileName = data[1];
        this.repository.loadData(fileName);
    }
}
