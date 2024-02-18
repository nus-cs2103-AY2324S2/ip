package duke;

/**
 * Represents a custom exception class specific to the Duke application.
 * DukeException is thrown to handle application-specific errors and exceptions.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMsg The error message associated with the exception.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
