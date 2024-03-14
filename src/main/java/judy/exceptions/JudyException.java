package judy.exceptions;

/**
 * A custom exception class for handling errors specific to the Judy application.
 */
public class JudyException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message describing the exceptional condition.
     */
    public JudyException(String message) {
        super("OOPS! " + message);
    }
}
