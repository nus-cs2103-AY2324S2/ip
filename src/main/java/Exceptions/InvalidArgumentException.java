package Exceptions;

import Exceptions.DudeException;

public class InvalidArgumentException extends DudeException {
    //Used to indicate that an argument for the command is invalid
    public InvalidArgumentException(String message) {
        super(message);
    }
}
