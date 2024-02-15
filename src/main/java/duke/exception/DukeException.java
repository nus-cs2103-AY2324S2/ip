package duke.exception;

/**
 * Represents a generic exception in the Duke application.
 */

public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    /**
     * Gets a default error message for the exception.
     *
     * @return A string representing the default error message.
     */
    @Override
    public String getMessage() {
        return "Unfortunately, ";
    }
}
