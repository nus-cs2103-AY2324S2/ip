package duke.exceptions.tasks;

import duke.exceptions.DukeException;

public class MissingTaskException extends DukeException {
    private static final String errorMessage = "The specified task does not exist.";
    public MissingTaskException() {
        super(errorMessage);
    }
}
