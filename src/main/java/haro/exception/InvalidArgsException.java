package haro.exception;

/**
 * Exception thrown when arguments provided are invalid.
 */
public class InvalidArgsException extends Exception {
    public InvalidArgsException(String message) {
        super(message);
    }
}
