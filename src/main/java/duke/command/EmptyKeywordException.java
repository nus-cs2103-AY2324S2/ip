package duke.command;

import duke.DukeException;

/**
 * Represents an exception that occurs when the keyword of a command is empty.
 */
public class EmptyKeywordException extends DukeException {
    public EmptyKeywordException(String message, String botMessage) {
        super(message, botMessage);
    }
}
