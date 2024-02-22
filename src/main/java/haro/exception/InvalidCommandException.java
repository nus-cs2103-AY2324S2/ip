package haro.exception;

/**
 * Exception thrown when invalid command is given.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
