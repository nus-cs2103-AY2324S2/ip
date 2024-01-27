public class EmptyTodoException extends PandaException {
    public EmptyTodoException() {
        super("OOPS! The description of a todo cannot be empty.");
    }
}
