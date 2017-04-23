package main.com.softuni.bashsoft.exceptions;

import main.com.softuni.bashsoft.static_data.ExceptionMessages;

public class DuplicateEntryInStructureException extends RuntimeException {

    public DuplicateEntryInStructureException(String entryName, String structureName) {
        super(String.format(ExceptionMessages.DUPLICATE_ENTRY, entryName, structureName));
    }

    public DuplicateEntryInStructureException(String message) {
        super(message);
    }
}