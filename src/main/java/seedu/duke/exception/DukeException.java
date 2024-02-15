package seedu.duke.exception;

/**
 * Represents an exception thrown when running the duke program
 */
public class DukeException extends Exception {
    /**
     * Constructor of the DukeException
     *
     * @param errorMessage The error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
