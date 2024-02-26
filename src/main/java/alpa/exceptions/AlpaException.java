package alpa.exceptions;

/**
 * This class represents an exception specific to the Alpa application.
 */
public class AlpaException extends Exception {

    /**
     * Constructs a new AlpaException with the specified detail message.
     *
     * @param message the detail message
     */
    public AlpaException(String message) {
        super(message);
    }
}
