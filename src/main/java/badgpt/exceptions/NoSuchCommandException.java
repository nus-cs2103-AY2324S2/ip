package badgpt.exceptions;

/**
 * Signals that the command entered does not exist.
 */
public class NoSuchCommandException extends CommandException {

    /**
     * Creates a new NoSuchCommandException with the specified message.
     *
     * @param message The error message.
     */
    public NoSuchCommandException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the exception.
     */
    @Override
    public String toString() {
        return "Please enter a valid command.";
    }
}
