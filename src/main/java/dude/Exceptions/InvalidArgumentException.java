package dude.Exceptions;

/**
 * The InvalidArgumentException class represents an exception that is thrown when an argument for a command is invalid.
 */
public class InvalidArgumentException extends DudeException {

    /**
     * Constructor for the InvalidArgumentException class.
     *
     * @param message The message of the exception.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
