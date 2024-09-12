package haro.exception;

/**
 * Exception thrown when event task is missing a from or to date.
 */
public class MissingEventTimeException extends Exception {
    public MissingEventTimeException(String message) {
        super(message);
    }
}
