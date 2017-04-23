package main.com.softuni.bashsoft.exceptions;

import main.com.softuni.bashsoft.static_data.ExceptionMessages;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException() {
        super(ExceptionMessages.NOT_ENROLLED_IN_COURSE);
    }

    public KeyNotFoundException(String message) {
        super(message);
    }
}