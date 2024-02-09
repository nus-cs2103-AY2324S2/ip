package duke;

public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("Invalid DateTime Format! Please use yyyy-mm-dd");
    }
}
