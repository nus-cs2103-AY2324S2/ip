package duke.command;

import duke.DukeException;

public class EmptyDateTimeException extends DukeException {
    public EmptyDateTimeException(String message, String botMessage) {
        super(message, botMessage);
    }
}
