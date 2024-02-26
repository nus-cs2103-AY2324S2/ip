package univus.exceptions;

/**
 * Custom exception for checking duplicates.
 */
public class DuplicatesException extends Throwable {
    /**
     * Constructs a UnivusException with a formatted error message.
     *
     * @param message Error message.
     */
    public DuplicatesException(String message) {
        super(message);
    }
}
