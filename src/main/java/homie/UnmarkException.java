package homie;

/**
 * UnmarkException class. Handles all exceptions related to unmark command.
 * Thrown when no index is given as a parameter, or index is out of range.
 */
public class UnmarkException extends Exception {
    /**
     * Constructor for UnmarkException class.
     *
     * @param message The error message.
     */
    public UnmarkException(String message) {
        super("Bruh... " + message + ". \nPlease follow the format: \nunmark {INDEX}");
    }
}
