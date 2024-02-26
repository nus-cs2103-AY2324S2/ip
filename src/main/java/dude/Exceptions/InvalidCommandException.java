package dude.Exceptions;

/**
 * The InvalidCommandException class represents an exception that is thrown when the command is invalid.
 */
public class InvalidCommandException extends DudeException {
    //Used to indicate that the command is invalid

    /**
     * Constructor for the InvalidCommandException class.
     *
     * @param message The message of the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
