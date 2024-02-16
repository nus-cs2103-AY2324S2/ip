package Exceptions;

import Exceptions.DudeException;

public class InvalidCommandException extends DudeException {
    //Used to indicate that the command is invalid
    public InvalidCommandException(String message) {
        super(message);
    }
}
