package duke.exception;
import duke.exception.DukeException;

public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException() {
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "The description cannot be empty.";
    }
}
