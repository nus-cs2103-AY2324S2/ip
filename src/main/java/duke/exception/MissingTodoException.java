package duke.exception;

/**
 * The class representing the exception thrown when the todo command is invalid.
 * */
public class MissingTodoException extends DukeException {
    public MissingTodoException() {
        super("Your todo's description must not be empty!");
    }
}
