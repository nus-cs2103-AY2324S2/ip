package duke.exception;

/**
 * The class representing the exception thrown when the event command is invalid.
 * */
public class MissingEventException extends DukeException {
    public MissingEventException() {
        super("Please enter a start and end for the event.");
    }
}
