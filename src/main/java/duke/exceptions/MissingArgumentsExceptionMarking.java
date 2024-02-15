package duke.exceptions;

/**
 * Exception class representing an error related to missing arguments in marking commands (mark, unmark, delete).
 * This exception is thrown when a marking command is missing the required index of the item in the list.
 */
public class MissingArgumentsExceptionMarking extends MissingArgumentsException {


    /**
     * Constructs a MissingArgumentsExceptionMarking with the specified detail message.
     *
     * @param message The detail message.
     */
    public MissingArgumentsExceptionMarking(String string) {
        super(string);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message guiding the user to include the index of the item in the marking command.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + " {index of item in list}";
    }
}
