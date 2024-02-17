package shuheng.exceptions;

/**
 * This class represents the error caused via an invalid date time.
 */
public class InvalidDateTimeException extends InvalidInputException {
    /**
     * Constructor for the exception.
     */
    public InvalidDateTimeException() {
        super("That's not a valid date time, remember to give it in a "
            + "`YYYY-MM-DD HHHH` format!");
    }
}
