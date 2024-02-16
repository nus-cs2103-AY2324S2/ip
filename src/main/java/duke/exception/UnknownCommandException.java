package duke.exception;

/**
 * The class representing the exception thrown when the entered command is not known.
 * */
public class UnknownCommandException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "I'm sorry, but I don't understand what you mean :(";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }
}
