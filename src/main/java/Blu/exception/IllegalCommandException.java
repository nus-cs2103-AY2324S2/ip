package blu.exception;

/**
 * Represents exceptions caused by from illegal or incorrect command formats supplied by the user.
 */
public class IllegalCommandException extends BluException {

    /**
     * Constructs a new IllegalCommandException with the specified detail message.
     * The detail message is prefixed with "Illegal Command Format:" to indicate the nature of the error.
     *
     * @param message The detail message which provides more information about the command format issue.
     */
    public IllegalCommandException(String message) {
        super("Illegal Command Format: " + message);
    }
}
