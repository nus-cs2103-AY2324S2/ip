package duke;

/**
 * Used to handle and convey application-specific errors.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with a specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
