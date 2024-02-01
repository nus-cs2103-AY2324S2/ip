package exception;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Input in the form:\ndeadline Homework /by 8/10/2024 2000");
    }
}