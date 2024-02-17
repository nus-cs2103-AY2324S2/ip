package duke.task;

import duke.DukeException;

/**
 * Represents an exception thrown when the date and time is not in the correct
 * format.
 */
public class DukeDateTimeParseException extends DukeException {
    public DukeDateTimeParseException(String message, String response) {
        super(message, response);
    }
}
