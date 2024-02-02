package exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Invalid Command");
    }
}
