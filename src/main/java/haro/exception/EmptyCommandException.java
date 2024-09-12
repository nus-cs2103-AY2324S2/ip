package haro.exception;

/**
 * Exception thrown when a command is provided with no arguments.
 */
public class EmptyCommandException extends Exception {
    public EmptyCommandException(String message) {
        super(message);
    }
}
