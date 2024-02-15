package seedu.duke.exception;

/**
 * Represents an exception when users key in invalid input value
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructor of InvalidInputException
     *
     * @param errorMessage The error message to be shown
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
