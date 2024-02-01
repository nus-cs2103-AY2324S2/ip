package exception;

public class InvalidTodoException extends DukeException {
    public InvalidTodoException() {
        super("Todo cannot be empty!");
    }
}
