package luke.exception;

/**
 * Represents an exception when the date is not in the correct format.
 */
public class DateException extends Exception {
    public DateException(String message) {
        super(message);
    }
}
