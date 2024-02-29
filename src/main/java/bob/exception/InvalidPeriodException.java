package bob.exception;

/**
 * Represents an error indicating that an invalid period has been given by the user.
 * An <code>InvalidPeriodException</code> object corresponds to an error indicating
 * that the start time of the period given by the user is later than its end time.
 */
public class InvalidPeriodException extends BobException {
    private static final String MESSAGE = "it ends before it starts???";

    /**
     * Returns an error indicating that an invalid period has been given by the user.
     */
    public InvalidPeriodException() {
        super(MESSAGE);
    }
}
