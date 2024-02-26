package dude.Exceptions;

/**
 * The DudeException class represents an exception that is specific to the Dude application.
 */
public class DudeException extends Exception {

    /**
     * Constructor for the DudeException class.
     *
     * @param message The message of the exception.
     */
    public DudeException(String message) {
        super(message);
    }
}
