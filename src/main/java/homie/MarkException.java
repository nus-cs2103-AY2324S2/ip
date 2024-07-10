package homie;

/**
 * MarkException thrown when no index is given as a parameter, or when index is out of range when marking a task
 * as done.
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
