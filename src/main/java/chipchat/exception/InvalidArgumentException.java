package chipchat.exception;

/**
 * Represents exceptions arising from invalid input arguments. Subclass of ArgumentException.
 */
public class InvalidArgumentException extends ArgumentException {
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
