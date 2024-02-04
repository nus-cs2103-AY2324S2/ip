package duke;

/**
 * To be thrown when command option parsing fails, which occurs for incorrect user input.
 */
public class DukeOptionParsingException extends DukeException {
    public DukeOptionParsingException(String message) {
        super(message);
    }
}
