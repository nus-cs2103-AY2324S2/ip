package snomexceptions;

/**
 * THis is the exception thrown when a user enters an invalid command.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException() {
        super("Please enter a valid command");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
