package duke.exception;

/**
 * The class representing the exception thrown when the deadline command is invalid.
 * */
public class MissingDeadlineException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "Please enter a deadline.";

    public MissingDeadlineException() {
        super(ERROR_MESSAGE);
    }
}
