package judy.exceptions;

/**
 * InvalidDateTimeException is a specific type of Judy Exception that shows
 * an error when invalid date time format is entered by users.
 */
public class InvalidDateTimeException extends JudyException {

    /**
     * Constructs an InvalidDateTimeException to show error message indicating
     * the expected date time input format.
     */
    public InvalidDateTimeException() {
        super(" Invalid date/time format! Please use yyyy-MM-dd HHmm. ");
    }
}

