package dude.exceptions;

/**
 * The InvalidDescriptionException class represents an exception that is thrown when the description for a task is invalid.
 */
public class InvalidDescriptionException extends DudeException {

    /**
     * Constructor for the InvalidDescriptionException class.
     *
     * @param message The message of the exception.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
