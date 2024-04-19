package duke.exceptions;

/**
 * Exception class representing an error related to missing arguments in an events command.
 * This exception is thrown when a deadlines command is missing the required parameter, which is the starting and
 * ending time.
 */
public class MissingArgumentsExceptionEvents extends MissingArgumentsExceptionTodo {

    /**
     * Constructs a MissingArgumentsExceptionMarking with the specified detail message.
     *
     * @param message The detail message.
     */
    public MissingArgumentsExceptionEvents(String string) {
        super(string);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message guiding the user to include the index of the item in the marking command.
     */
    @Override
    public String getMessage() {
        return super.getMessage()
                + " /from {your starting time}"
                + "\n    {space}/to {your ending time}";
    }
}
