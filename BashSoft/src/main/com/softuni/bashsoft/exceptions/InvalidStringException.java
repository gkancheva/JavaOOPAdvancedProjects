package main.com.softuni.bashsoft.exceptions;

import main.com.softuni.bashsoft.static_data.ExceptionMessages;

public class InvalidStringException extends RuntimeException {

    public InvalidStringException() {
        super(ExceptionMessages.NULL_OR_EMPTY_VALUE);
    }

    public InvalidStringException(String message) {
        super(message);
    }

}