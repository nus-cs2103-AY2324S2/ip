package numerator.exceptions;

/**
 * Signals that an exception has occurred in Numerator.
 */
public class NumeratorException extends Exception {
    /**
     * Constructs a NumeratorException with the specified detail message.
     *
     * @param message should contain information about the exception.
     */
    public NumeratorException(String message) {
        super(message);
    }
}
