package duke.task;

import duke.DukeException;

/**
 * Represents an exception thrown when the data file is not in the correct
 * format.
 */
public class InvalidDataFormatException extends DukeException {
    public InvalidDataFormatException(String message, String botMessage) {
        super(message, botMessage);
    }
}
