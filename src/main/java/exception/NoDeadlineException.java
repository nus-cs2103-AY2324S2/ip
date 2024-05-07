package exception;

/**
 * Represents an exception when a deadline is not specified.
 */
public class NoDeadlineException extends IncompleteCommandException {
    /**
     * Constructor for NoDeadlineException.
     */
    public NoDeadlineException() {
        super("Deadline");
    }

    /**
     * Returns the error message when a deadline is not specified.
     */
    @Override
    public String toString() {
        return "OOPS!!! You have not specified a deadline.";
    }
}
