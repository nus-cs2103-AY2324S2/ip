package exception;

/**
 * InvalidCommandException is a specific type of DukeException that represents an error
 * when an invalid command is encountered in the Duke application.
 * It provides a predefined error message indicating that the command is invalid.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs an InvalidCommandException with a predefined error message indicating
     * that the encountered command is invalid.
     */
    public InvalidCommandException() {
        super("Invalid Command");
    }
}
