package seedu.chatteroo;

/**
 * Represents an exception that is thrown when Chatteroo encounters an error.
 */
public class ChatterooException extends Exception {

    /**
     * Constructor for the ChatterooException class.
     * @param message The error message for the Chatteroo exception.
     */
    public ChatterooException(String message) {
        super(message);
    }
}
