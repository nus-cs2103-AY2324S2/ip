package chipchat.exception;

/**
 * Represents runtime exceptions related to Chipchat. Subclass of RuntimeException.
 */
public class ChipchatException extends RuntimeException {
    public ChipchatException(String msg) {
        super(msg);
    }
}
