package duke;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param errorMessage The error message to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
