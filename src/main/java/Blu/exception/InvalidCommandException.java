package blu.exception;

/**
 * Represents exceptions caused by from invalid commands supplied by the user.
 */
public class InvalidCommandException extends BluException {

    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     * The detail message is prefixed with "Command not found:" to indicate the nature of the error.
     *
     * @param message The detail message which provides more information about the invalid command.
     */
    public InvalidCommandException(String message) {
        super("Command not found: " + message);
    }
}
