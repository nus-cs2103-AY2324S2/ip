package duke;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param message The message to be displayed when the exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
