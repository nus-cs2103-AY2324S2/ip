package judy.exceptions;

/**
 * InvalidTodoException is a specific type of Judy Exception that shows
 * an error when invalid todo format or empty description is entered by users.
 */
public class InvalidTodoException extends JudyException {

    /**
     * Constructs an InvalidTodoException to show error message indicating
     * the expected todo input format.
     */
    public InvalidTodoException() {
        super("The description of a todo cannot be empty!\n"
                + "Eg format: todo <Description>");
    }
}
