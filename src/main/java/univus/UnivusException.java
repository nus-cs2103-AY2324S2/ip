package univus;

/**
 * Custom exception for univus class.
 */
public class UnivusException extends Throwable {
    /**
     * Constructs a UnivusException with a formatted error message.
     *
     * @param message Error message.
     */
    public UnivusException(String message) {
        super(message);
    }
}
