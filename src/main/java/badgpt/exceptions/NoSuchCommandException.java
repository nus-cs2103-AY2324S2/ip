package badgpt.exceptions;

public class NoSuchCommandException extends CommandException {
    public NoSuchCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Please enter a valid command.";
    }
}
