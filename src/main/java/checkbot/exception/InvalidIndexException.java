package checkbot.exception;

/**
 * Represents an exception where the user inputs an invalid index.
 */
public class InvalidIndexException extends CheckbotException {
    public InvalidIndexException(String index) {
        super("\"" + index + "\" is not a number.");
    }

    /**
     * Constructs an InvalidIndexException, given an index and length of the task list.
     *
     * @param index  The index which the user tried to access.
     * @param length The length of the task list.
     */
    public InvalidIndexException(int index, int length) {
        super(length == 0
                ? "Please add tasks into your list before doing that!"
                : "Task number " + index
                + " does not exist. "
                + (length > 1
                ? "Only task numbers 1 - "
                + length + " are accepted."
                : "You only have 1 task in your list."));
    }
}
