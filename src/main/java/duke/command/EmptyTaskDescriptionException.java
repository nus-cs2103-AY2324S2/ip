package duke.command;

import duke.DukeException;

/**
 * Represents an exception that occurs when the user does not provide a date and
 * time for a deadline or event.
 */
public class EmptyTaskDescriptionException extends DukeException {
    public EmptyTaskDescriptionException(String message, String botMessage) {
        super(message, botMessage);
    }
}
