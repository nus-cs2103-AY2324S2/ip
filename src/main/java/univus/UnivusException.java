package univus;

/**
 * Custom exception for univus class.
 */
public class UnivusException extends Throwable {
    /**
     * Constructs a UnivusException with a formatted error message.
     *
     * @param Message Error message.
     */
    public UnivusException(String Message) {
        super(Message);
    }
}
