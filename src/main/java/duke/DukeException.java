package duke;

/**
 * The DukeException class represents an exception specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
