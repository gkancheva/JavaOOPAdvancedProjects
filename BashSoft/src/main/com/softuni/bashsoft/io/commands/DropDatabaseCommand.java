package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.repository.StudentRepository;
import main.com.softuni.bashsoft.static_data.Messages;

@Alias("dropdb")
public class DropDatabaseCommand extends Command {

    @Inject
    private StudentRepository studentRepository;

    public DropDatabaseCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() {
        String[] data = this.getData();
        if (data.length != 1) {
            throw new InvalidInputException(this.getInput());
        }

        this.studentRepository.unloadData();
        OutputWriter.writeMessageOnNewLine(Messages.DB_DROPPED);
    }
}
