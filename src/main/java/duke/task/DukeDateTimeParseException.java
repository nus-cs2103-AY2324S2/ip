package duke.task;

import duke.DukeException;

public class DukeDateTimeParseException extends DukeException {
    public DukeDateTimeParseException(String message, String response) {
        super(message, response);
    }
}