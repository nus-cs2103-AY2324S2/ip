package yoda.exceptions;
/**
 * Exception thrown when an unrecognized command is encountered.
 * This typically happens when user input does not match any of the expected commands.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructor for UnknownCommandException.
     */
    public UnknownCommandException() {
        super("Unknown command. Try again, you must.");
    }
}
