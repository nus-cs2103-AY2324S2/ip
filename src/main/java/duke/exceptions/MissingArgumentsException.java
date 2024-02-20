package duke.exceptions;

/**
 * Exception class representing a generic error related to missing arguments in a command.
 * This exception is thrown when a command is missing the required parameters or arguments.
 */
public class MissingArgumentsException extends Exception {

    /**
     * Constructs a MissingArgumentsException with the specified detail message.
     *
     * @param message The detail message indicating the missing arguments.
     */
    public MissingArgumentsException(String message) {
        super(message);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message indicating that there are missing arguments in the command.
     */
    @Override
    public String getMessage() {
        return "    You have missing arguments:\n"
                + "    Try " + super.getMessage();
    }
}
