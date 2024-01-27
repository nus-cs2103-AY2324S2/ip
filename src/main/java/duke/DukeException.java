package duke;

/**
 * Represents a DukeException that will be thrown in the event of an error.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param errorMessage Error message to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}