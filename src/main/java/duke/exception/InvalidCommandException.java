package duke.exception;

/**
 * Represents the exceptions thrown in the program related to invalid commands.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructor for an InvalidCommandException,
     * which initialises it with its error message.
     *
     * @param message Description of the invalid command error.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String errorMessage() {
        return "Invalid Command";
    }
}
