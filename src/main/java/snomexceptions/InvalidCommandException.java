package snomexceptions;

public class InvalidCommandException extends Exception {

    public InvalidCommandException() {
        super("Please enter a valid command");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
