package main.com.softuni.bashsoft.exceptions;

import main.com.softuni.bashsoft.static_data.ExceptionMessages;

public class InvalidFilter extends RuntimeException{
    public InvalidFilter() {
        super(ExceptionMessages.INVALID_FILTER);
    }
}
