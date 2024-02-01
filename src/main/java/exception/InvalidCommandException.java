package exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Details cannot be empty!");
    }
}
