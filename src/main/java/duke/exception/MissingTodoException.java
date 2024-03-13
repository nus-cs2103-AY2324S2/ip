package duke.exception;

/**
 * The class representing the exception thrown when the todo command is invalid.
 * */
public class MissingTodoException extends DukeException {
    /* The error message to be displayed to the user. */
    public static final String ERROR_MESSAGE = "Your todo's description must not be empty!";

    public MissingTodoException() {
        super(ERROR_MESSAGE);
    }
}
