package duke.exceptions;

/**
 * Exception class representing an error when an invalid command is entered.
 * This exception is thrown when the user inputs a command that is not recognized.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs an InvalidCommandException with the specified detail message.
     *
     * @param message The detail message indicating that an invalid command was entered.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Overrides the getMessage() method to provide a formatted error message.
     *
     * @return A formatted error message indicating that an invalid command was entered.
     */
    @Override
    public String getMessage() {
        return "    " + super.getMessage()
                + "\n    You have entered an invalid "
                + "command:\n"
                + "    Try todo, event, deadline, list, delete, "
                + "\n    mark {index},"
                + "\n    unmark {index},"
                + "\n    or priority {index} {high/low}.";
    }
}
