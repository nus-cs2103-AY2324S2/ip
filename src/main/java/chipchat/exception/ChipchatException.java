package chipchat.exception;

/**
 * Represents runtime exceptions related to Chipchat. Subclass of RuntimeException.
 */
public class ChipchatException extends RuntimeException {
    /**
     * Initializes the exception.
     *
     * @param msg the error message
     */
    public ChipchatException(String msg) {
        super(msg);
    }
}
