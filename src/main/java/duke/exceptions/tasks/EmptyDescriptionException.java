package duke.exceptions.tasks;

import duke.exceptions.DukeException;

public class EmptyDescriptionException extends DukeException {
    private static final String errorMessage = "The description of a task cannot be empty.";
    public EmptyDescriptionException() {
        super(errorMessage);
    }
}
