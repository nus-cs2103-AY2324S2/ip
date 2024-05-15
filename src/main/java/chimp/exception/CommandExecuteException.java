package chimp.exception;

/**
 * Exception thrown when there is an error executing a command.
 */
public class CommandExecuteException extends ChimpException {

    /**
     * Constructs a CommandExecuteException with the specified error message.
     *
     * @param message the error message
     */
    public CommandExecuteException(String message) {
        super(message);
    }

    /**
     * Returns the error message of this exception.
     *
     * @return the error message
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
