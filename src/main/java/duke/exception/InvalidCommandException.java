package duke.exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String errorMessage() {
        return "Invalid Command";
    }
}
