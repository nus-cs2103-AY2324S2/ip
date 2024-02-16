package Exceptions;

import Exceptions.DudeException;

public class InvalidFormatException extends DudeException {
    //Used to indicate that the format is invalid
    public InvalidFormatException(String message) {
        super(message);
    }
}
