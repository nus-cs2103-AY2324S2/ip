package unim.application;

/**
 * UnimException class throws exceptions specific to the Unim application.
 */
public class UnimException extends IllegalArgumentException {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public UnimException(String message) {
        super(message);
    }

    /**
     * Constructs a DukeException with the specified error message and a nested exception.
     *
     * @param message The error message associated with the exception.
     * @param e       The nested exception that caused this DukeException.
     */
    public UnimException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Creates a DukeException for a generic loading error without a nested exception.
     *
     * @return A DukeException indicating an error during the loading process.
     */
    public static UnimException createLoadingError() {
        return new UnimException("Error loading files");
    }

    /**
     * Creates a DukeException for a loading error with a nested exception.
     *
     * @param e The nested exception that caused the loading error.
     * @return A DukeException indicating an error during the loading process with details from the nested exception.
     */
    public static UnimException showLoad(Exception e) {
        return new UnimException("Error loading files", e);
    }
}
