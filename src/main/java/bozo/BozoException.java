package bozo;

/**
 * Represents an exception specific to Bozo.
 */
public class BozoException extends Exception {
    /**
     * Constructs a new BozoException with the specified error message.
     *
     * @param message The error message.
     */
    public BozoException(String message) {
        super(message);
    }
}
