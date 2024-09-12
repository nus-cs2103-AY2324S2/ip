package judy.exceptions;

/**
 * InvalidDeadlineException is a specific type of Judy Exception that shows
 * an error when invalid deadline format is entered by users.
 */
public class InvalidDeadlineException extends JudyException {

    /**
     * Constructs an InvalidDeadlineException to show error message indicating
     * the expected deadline input format.
     */
    public InvalidDeadlineException() {
        super("Invalid deadline format! Try this: \n"
                + "deadline <Description> /by yyyy-MM-dd HHmm");
    }
}
