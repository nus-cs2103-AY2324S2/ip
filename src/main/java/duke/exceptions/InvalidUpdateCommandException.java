package duke.exceptions;

public class InvalidUpdateCommandException extends InvalidCommandException {
    public InvalidUpdateCommandException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "    You have entered an invalid command:"
                + "\n    task cannot have both /from|/to date-time and /by date-time";
    }
}
