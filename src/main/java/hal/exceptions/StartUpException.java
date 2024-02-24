package hal.exceptions;

/**
 * The `StartUpException` class represents an exception related to startup issues in the Duke application.
 */
public class StartUpException extends HalException {
    public StartUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
