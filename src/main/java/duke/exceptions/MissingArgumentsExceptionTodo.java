package duke.exceptions;

/**
 * Exception class representing an error related to missing arguments in a todo command.
 * This exception is thrown when a todo command is missing the required task description.
 */
public class MissingArgumentsExceptionTodo extends MissingArgumentsException {

    /**
     * Constructs a MissingArgumentsExceptionTodo with the specified detail message.
     */
    public MissingArgumentsExceptionTodo(String string) {
        super(string);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message guiding the user to include the task description in the todo command.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + " {your item}";
    }

}
