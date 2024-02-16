package duke.exception;

/**
 * The class representing the exception thrown when the event command is invalid.
 * */
public class MissingEventException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "Please enter a start and end for the event.";

    public MissingEventException() {
        super(ERROR_MESSAGE);
    }
}
