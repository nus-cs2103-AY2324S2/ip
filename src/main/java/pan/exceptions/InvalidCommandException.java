package pan.exceptions;

/**
 * InvalidCommandException - A custom exception
 * @author Jerome Goh
 */
public class InvalidCommandException extends Exception {
    /**
     * Creates a InvalidCommandException instance.
     *
     * @param message error message of the given exception
     */
    public InvalidCommandException(String message) {
        super("Invalid Command Exception: " + message);
    }
}
