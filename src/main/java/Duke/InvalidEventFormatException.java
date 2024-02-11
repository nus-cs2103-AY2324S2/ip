package duke;

public class InvalidEventFormatException extends DukeException {
    public InvalidEventFormatException() {
        super("/from and /to arguments needed for event");
    }
}
