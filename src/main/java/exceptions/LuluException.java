package exceptions;

/**
 * An exception class specific to the Lulu application, extending the base {@code Exception}.
 * This exception is used to handle errors and exceptional conditions within the Lulu application.
 */
public class LuluException extends Exception {
    public LuluException(String message) {
        super(message);
    }
}
