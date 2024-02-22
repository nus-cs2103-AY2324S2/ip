package duke;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Initializes a DukeException with the error message.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        super(message);
    }
}
