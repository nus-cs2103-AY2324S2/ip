package blu.exception;

/**
 * Represents exceptions caused by from illegal or incorrect command parameters supplied by the user.
 */
public class IllegalParameterException extends BluException {

    /**
     * Constructs a new IllegalParameterException with the specified detail message.
     * The detail message is prefixed with "Illegal Parameter:" to indicate the nature of the error.
     *
     * @param message The detail message which provides more information about the command parameter issue.
     */
    public IllegalParameterException(String message) {
        super("Illegal Parameter: " + message);
    }
}
