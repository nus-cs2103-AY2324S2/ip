package duke.exceptions;

/**
 * Throws error message due to invalid Syntax written in the command.
 */
public class SyntaxException extends DukeException {

    public SyntaxException(String message) {
        super(message);
    }
}