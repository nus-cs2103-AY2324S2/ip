package drew.exceptions;

/**
 * This exception covers commands that are not recognised.
 */
public class UnknownCommandException extends IllegalArgumentException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
