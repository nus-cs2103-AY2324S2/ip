package exceptions.tasks;

import exceptions.DukeException;

public class EmptyDescriptionException extends DukeException {
    private final static String errorMessage = "The description of a task cannot be empty.";
    public EmptyDescriptionException() {
        super(errorMessage);
    }
}
