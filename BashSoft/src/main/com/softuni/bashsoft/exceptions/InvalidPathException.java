package main.com.softuni.bashsoft.exceptions;

import main.com.softuni.bashsoft.static_data.ExceptionMessages;

public class InvalidPathException extends RuntimeException {


    public InvalidPathException() {
        super(ExceptionMessages.INVALID_PATH);
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
