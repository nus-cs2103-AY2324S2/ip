package exception;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Input in the form:\ndeadline {taskName} /by {dueDate}");
    }
}