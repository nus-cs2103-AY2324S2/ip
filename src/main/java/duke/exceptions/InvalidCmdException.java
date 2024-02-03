package duke.exceptions;

/**
 * Issues an Invalid Command Exception if the Duke command cannot be completed.
 */
public class InvalidCmdException extends DukeException {
    /**
     * Constructs an <code>InvalidCmdException</code> if given Duke command is invalid.
     * This could mean if the command cannot be parsed, or if arguments provided are invalid.
     *
     * @param msg Exception message.
     */
    public InvalidCmdException(String msg) {
        super(msg);
    }
}
