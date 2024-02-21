package duke.exceptions;

/**
 * Exception thrown when the date/time format can't be recognized by the DukeDateFormator.
 */
public class InvalidDateTimeException extends BaseException {
    /**
     * Calls parent constructor with specific pre-defined message.
     */
    public InvalidDateTimeException() {
        super("Incorrect format of date, please follow the convention: yyyy-mm-dd");
    }

    /**
     * Calls parent constructor with specific pre-defined message.
     */
    public InvalidDateTimeException(String message) {
        super("Incorrect date: " + message);
    }
}
