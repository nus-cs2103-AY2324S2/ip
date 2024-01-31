package duke.exceptions;

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
