package haro.exception;

/**
 * Exception thrown when deadline task is missing a due date.
 */
public class MissingDuedateException extends Exception {
    public MissingDuedateException(String message) {
        super(message);
    }
}
