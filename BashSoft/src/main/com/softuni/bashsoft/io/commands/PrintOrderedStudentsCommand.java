package main.com.softuni.bashsoft.io.commands;

import main.com.softuni.bashsoft.annotations.Alias;
import main.com.softuni.bashsoft.annotations.Inject;
import main.com.softuni.bashsoft.exceptions.InvalidInputException;
import main.com.softuni.bashsoft.io.OutputWriter;
import main.com.softuni.bashsoft.repository.StudentRepository;
import main.com.softuni.bashsoft.static_data.ExceptionMessages;

@Alias("order")
public class PrintOrderedStudentsCommand extends Command {

    @Inject
    private StudentRepository studentRepository;

    public PrintOrderedStudentsCommand(String input, String[] data) {
        super(input, data);
    }

    @Override
    public void execute() {
        String[] data = this.getData();
        if (data.length != 5) {
            throw new InvalidInputException(this.getInput());
        }
        String courseName = data[1];
        String orderType = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();
        this.tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);
    }

    private void tryParseParametersForOrder(
            String takeCommand, String takeQuantity,
            String courseName, String orderType) {
        if (!takeCommand.equals("take")) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_COMMAND);
            return;
        }
        if (takeQuantity.equals("all")) {
            this.studentRepository.orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.studentRepository.orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }
}
