package bob.exception;

/**
 * Represents an error indicating that an invalid task index has been given by the user.
 * An <code>InvalidTaskIndexException</code> object corresponds to an error indicating
 * that the task index given by the user is not a number or is out of range.
 */
public class InvalidTaskIndexException extends BobException {
    private static final String MESSAGE_NAN = "%s? that's not even a number";
    private static final String MESSAGE_OUT_OF_BOUNDS = "bro i want a number between 1 and %s but you gave me %s";

    /**
     * Returns an error indicating that an invalid task index has been given by the user.
     *
     * @param parsedString The task index given by the user.
     */
    public InvalidTaskIndexException(String parsedString) {
        super(String.format(MESSAGE_NAN, parsedString));
    }

    /**
     * Returns an error indicating that an invalid task index has been given by the user.
     *
     * @param parsedString The task index given by the user.
     * @param numberOfTasks The number of tasks currently in the task list,
     *                      to indicate the expected range of task index.
     */
    public InvalidTaskIndexException(String parsedString, int numberOfTasks) {
        super(String.format(MESSAGE_OUT_OF_BOUNDS, numberOfTasks, parsedString));
    }
}
