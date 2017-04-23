package main.com.softuni.bashsoft.exceptions;

import main.com.softuni.bashsoft.static_data.ExceptionMessages;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException(String command) {
        super(String.format(ExceptionMessages.INVALID_COMMAND_MESSAGE, command));
    }
}
