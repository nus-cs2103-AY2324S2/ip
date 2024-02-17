package denify.exception;

/**
 * Represents an exception specific to the Denify application, extending the general `Exception` class
 * to handle custom exceptions within Denify.
 */
public class DenifyException extends Exception {
    /**
     * Constructs a `DenifyException` with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DenifyException(String message) {
        super(message);
    }
}
