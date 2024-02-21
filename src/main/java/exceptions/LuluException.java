package exceptions;

/**
 * An exception class specific to the lulu.Lulu application, extending the base {@code Exception}.
 * This exception is used to handle errors and exceptional conditions within the lulu.Lulu application.
 */
public class LuluException extends Exception {
    public LuluException(String message) {
        super(message);
    }
}
