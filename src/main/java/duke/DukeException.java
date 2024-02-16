package duke;

/**
 * Represents an exception that is specific to the duke assistant program.
 */
public class DukeException extends Exception {
    /**
     * Constructor for creating a DukeException object.
     *
     * @param message String containing more detailed information about the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
