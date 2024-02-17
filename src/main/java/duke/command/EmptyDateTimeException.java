package duke.command;

import duke.DukeException;

/**
 * Represents an exception that occurs when the user does not provide a date and
 * time for a deadline or event.
 */
public class EmptyDateTimeException extends DukeException {
    public EmptyDateTimeException(String message, String botMessage) {
        super(message, botMessage);
    }
}
