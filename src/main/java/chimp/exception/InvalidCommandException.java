package chimp.exception;
/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends ChimpException {
    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Returns the detail message of this exception.
     *
     * @return the detail message
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
