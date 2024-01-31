package duke;

public class InvalidDeadlineFormatException extends DukeException {
    public InvalidDeadlineFormatException() {
        super("/by argument needed for deadline");
    }
}
