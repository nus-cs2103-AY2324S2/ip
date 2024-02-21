package pyrite;

/**
 * Exception class for Pyrite.
 */
public class PyriteException extends UnsupportedOperationException {
    private String error;
    public PyriteException(String error) {
        this.error = error;
    }
    @Override
    public String toString() {
        return error;
    }
}
