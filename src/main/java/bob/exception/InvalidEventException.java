package bob.exception;

/**
 * Represents an error indicating that an invalid event has been given by the user.
 * An <code>InvalidEventException</code> object corresponds to an error indicating
 * that the start time of the event given by the user is later than its end time.
 */
public class InvalidEventException extends BobException {
    private static final String MESSAGE = "wow your event ends before it starts";

    /**
     * Returns an error indicating that an invalid event has been given by the user.
     */
    public InvalidEventException() {
        super(MESSAGE);
    }
}
