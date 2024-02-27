package panda.exception;

public class EmptyTodoException extends PandaException {
    /**
     * Constructs a new EmptyTodoException with a default message.
     */
    public EmptyTodoException() {
        super("OOPS! The description of a todo cannot be empty.");
    }
}
