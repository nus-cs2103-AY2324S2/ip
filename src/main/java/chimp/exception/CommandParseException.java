package chimp.exception;
/**
 * Represents an exception that occurs when a command cannot be parsed correctly.
 * This exception is thrown when there is an error in parsing the user input into a valid command.
 */
public class CommandParseException extends ChimpException {
    /**
     * Constructs a CommandParseException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public CommandParseException(String message) {
        super(message);
    }

    /**
     * Returns the error message associated with this exception.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
