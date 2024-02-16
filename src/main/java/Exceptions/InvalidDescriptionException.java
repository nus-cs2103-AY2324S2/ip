package Exceptions;

import Exceptions.DudeException;

public class InvalidDescriptionException extends DudeException {
    //Used to indicate that the description is invalid
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
