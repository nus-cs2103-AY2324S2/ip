package duke.exception;

/**
 * The class representing the exception thrown when the deadline command is invalid.
 * */
public class MissingDeadlineException extends DukeException {
    public MissingDeadlineException() {
        super("Please enter a deadline.");
    }
}
