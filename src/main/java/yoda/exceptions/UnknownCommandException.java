package yoda.exceptions;
/**
 * Exception thrown when an unrecognized command is encountered.
 * This typically happens when user input does not match any of the expected commands.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs an UnknownCommandException with a detailed message.
     * The message usually indicates that the user input did not match any known command.
     *
     * @param message A string detailing the reason for the exception,
     *                such as "Unknown command entered".
     */
    public UnknownCommandException(String message) {
        super(message); // Call to the superclass (Exception) constructor with the detailed message
    }
}
