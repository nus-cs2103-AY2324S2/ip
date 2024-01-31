package duke.command;

import duke.DukeException;

/**
 * Represents an exception that occurs when the user enters an unparsable
 * command.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message, String botMessage) {
        super(message, botMessage);
    }
}
