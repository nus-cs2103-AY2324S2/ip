package DukeException;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "    " + super.getMessage() + "\n    You have entered an invalid command:\n" +
                "    Try todo, event, deadline, list instead.";
    }
}
