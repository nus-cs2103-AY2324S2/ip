package chipchat.exception;

/**
 * Represents exceptions arising from invalid input arguments. Subclass of ArgumentException.
 */
public class InvalidArgumentException extends ArgumentException {
    /**
     * Initializes the exception.
     *
     * @param msg the error message
     */
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
