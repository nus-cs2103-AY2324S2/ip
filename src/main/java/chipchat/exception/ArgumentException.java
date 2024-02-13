package chipchat.exception;

/**
 * Represents exceptions related to input arguments. Subclass of ChipchatException.
 */
public class ArgumentException extends ChipchatException {
    public ArgumentException(String msg) {
        super(msg);
    }
}
