package ben.exceptions;

/**
 * Custom exception class for handling application-specific errors in the Ben task management application.
 */
public class BenException extends Exception {

    /**
     * Constructs a new BenException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public BenException(String message) {
        super(message);
    }
}
