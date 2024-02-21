package pyrite;

/**
 * Exception class for Pyrite.
 */
public class PyriteException extends UnsupportedOperationException {
    private String error;
    /**
     * Constructs a PyriteException.
     *
     * @param error Error message.
     */
    public PyriteException(String error) {
        this.error = error;
    }
    /**
     * Generate a string representation of the exception.
     */
    @Override
    public String toString() {
        return error;
    }
}
