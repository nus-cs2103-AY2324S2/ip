package exceptions;

/**
 *  Represents exception relating to invalid commands.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super();
    }
    public InvalidCommandException(String message) {
        super(message);
    }
}
