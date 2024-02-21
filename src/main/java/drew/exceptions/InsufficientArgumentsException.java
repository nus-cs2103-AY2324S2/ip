package drew.exceptions;

/**
 * This exception covers commands that do not have enough arguments.
 */
public class InsufficientArgumentsException extends IllegalArgumentException {
    public InsufficientArgumentsException(String message) {
        super(message);
    }
}
