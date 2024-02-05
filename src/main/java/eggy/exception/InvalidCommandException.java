package eggy.exception;

/**
 * Represents an exception when the user inputs an invalid command.
 */
public class InvalidCommandException extends EggyException {
    /**
     * Constructs an InvalidCommandException.
     */
    public InvalidCommandException() {
        super(" I'm sorry, but I don't know what that means :-(");
    }
}
