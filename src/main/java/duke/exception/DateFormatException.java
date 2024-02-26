package duke.exception;

/**
 * Exception thrown when user inputs dates in unrecognised format
 */
public class DateFormatException extends Exception {

    /**
     * Constructor for a new DateFormatException
     */
    public DateFormatException() {
        super("Please check the format of your date input! Accepted format: DD/MM/YYYY TTTT");
    }
}
