package duke.exception;

import duke.exception.DukeException;

public class UnknownCommandException extends DukeException {

    public UnknownCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "I didn't understand that command.";
    }
}