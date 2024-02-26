package homie;

/**
 * Mark exception class. Handles all exceptions related to mark command.
 * Thrown when no index is given as a parameter, or index out of range.
 */
public class MarkException extends Exception {
    /**
     * Constructor for MarkException class.
     *
     * @param message The error message.
     */
    public MarkException(String message) {
        super("Bruh... " + message + "\nPlease follow the format:\nmark {INDEX}");
    }
}
