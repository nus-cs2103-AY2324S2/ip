package duke.exceptions;

/**
 * Issues an Invalid Task Exception when a <code>Task</code> constructed is invalid.
 */
public class InvalidTaskException extends DukeException {
    /**
     * Constructs an <code>InvalidTaskException</code> when a <code>Task</code>
     * constructed is invalid.
     *
     * @param msg Exception message.
     */
    public InvalidTaskException(String msg) {
        super(msg);
    }
}
