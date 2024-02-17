package exceptions;

/**
 * Handles date and time-related issues.
 * This exception is thrown when there is an issue with parsing or validating date and time.
 */
public class RyanGoslingDateTimeException extends RyanGoslingException {

    /**
     * Constructs a new RyanGoslingDateTimeException with the specified detail message.
     *
     * @param message The detail message explaining the date and time issue.
     */
    public RyanGoslingDateTimeException(String message) {
        super(message);
    }
}
