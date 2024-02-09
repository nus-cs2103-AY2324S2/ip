package duke.exceptions;

/**
 * Represents the Duke Exception class to throw errors.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the Duke Exception class.
     *
     * @param errorMessage Message to display to user.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
