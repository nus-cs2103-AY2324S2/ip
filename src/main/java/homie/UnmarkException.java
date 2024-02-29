package homie;

/**
 * UnmarkException thrown when no index is given as a parameter, or when index is out of range when unmarking a task.
 */
public class UnmarkException extends Exception {
    /**
     * Constructor for UnmarkException class.
     *
     * @param message The error message.
     */
    public UnmarkException(String message) {
        super("Bruh... " + message + "\nPlease follow the format:\nunmark {INDEX}");
    }
}
