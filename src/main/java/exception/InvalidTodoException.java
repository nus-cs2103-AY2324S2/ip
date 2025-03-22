package exception;

/**
 * InvalidTodoException is a specific type of DukeException that represents an error
 * when an invalid todo format is encountered in the Duke application.
 * It provides a predefined error message indicating that the todo cannot be empty.
 */
public class InvalidTodoException extends DukeException {

    /**
     * Constructs an InvalidTodoException with a predefined error message indicating
     * that the todo cannot be empty.
     */
    public InvalidTodoException() {
        super("Input in the form:\ntodo Cook");
    }
}
