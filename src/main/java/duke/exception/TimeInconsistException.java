package duke.exception;

import duke.exception.DukeException;

public class TimeInconsistException extends DukeException {
    public TimeInconsistException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("%s your [to] should not before [from]", super.getMessage());
    }
}
