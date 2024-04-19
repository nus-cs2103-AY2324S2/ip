package duke.exceptions;

/**
 * Represents an exception thrown when there are missing arguments in a "find" command.
 * Extends the MissingArgumentsException class.
 */
public class MissingArgumentsExceptionFind extends MissingArgumentsException {

    /**
     * Constructs a MissingArgumentsExceptionFind with the specified error message.
     *
     * @param string The error message indicating missing arguments.
     */
    public MissingArgumentsExceptionFind(String string) {
        super(string);
    }


    /**
     * Overrides the getMessage method to provide a more specific error message
     * for the "find" command, suggesting the required format.
     *
     * @return The formatted error message.
     */
    public String getMessage() {
        return super.getMessage() + " {your item}";
    }
}
