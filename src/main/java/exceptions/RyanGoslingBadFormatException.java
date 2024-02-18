package exceptions;

/**
 * Handles bad format issues.
 * This exception is thrown when there is an issue with the format of user inputs.
 */
public class RyanGoslingBadFormatException extends RyanGoslingException {

    /**
     * Constructs a new RyanGoslingBadFormatException with the specified detail message.
     *
     * @param message The detail message explaining the bad format issue.
     */
    public RyanGoslingBadFormatException(String message) {
        super(message);
    }
}
