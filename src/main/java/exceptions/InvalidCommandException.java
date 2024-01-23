package exceptions;

public class InvalidCommandException extends BluException {
    public InvalidCommandException(String message) {
        super("Command not found: " + message);
    }
}
