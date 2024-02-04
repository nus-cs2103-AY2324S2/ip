package duke;

/**
 * Represents an exception where the command format is invalid.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
