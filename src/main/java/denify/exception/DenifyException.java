package denify.exception;

/**
 * The `DenifyException` class represents an exception specific to the Denify application.
 * It extends the general `Exception` class to handle custom exceptions within Denify.
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
