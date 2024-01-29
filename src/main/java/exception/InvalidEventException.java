package exception;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Input in the form:\nevent {taskName} /from {startDate} /to {endDate}");
    }
}
