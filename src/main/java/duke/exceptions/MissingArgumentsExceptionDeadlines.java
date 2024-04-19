package duke.exceptions;

/**
 * Exception class representing an error related to missing arguments in a deadlines command.
 * This exception is thrown when a deadlines command is missing the required parameter, which is the deadline time.
 */
public class MissingArgumentsExceptionDeadlines extends MissingArgumentsExceptionTodo {

    /**
     * Constructs a MissingArgumentsExceptionDeadlines with the specified detail message.
     *
     * @param message The detail message.
     */
    public MissingArgumentsExceptionDeadlines(String string) {
        super(string);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message guiding the user to include the deadline time in the deadlines command.
     */
    @Override
    public String getMessage() {
        return super.getMessage()
                + " /by {your deadline}";
    }

}
