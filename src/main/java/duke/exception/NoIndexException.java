package duke.exception;

import duke.exception.DukeException;
public class NoIndexException extends DukeException {

    public NoIndexException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Please provide the task index.";
    }
}