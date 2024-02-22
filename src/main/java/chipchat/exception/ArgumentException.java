package chipchat.exception;

/**
 * Represents exceptions related to input arguments. Subclass of ChipchatException.
 */
public class ArgumentException extends ChipchatException {
    /**
     * Initializes the exception.
     *
     * @param msg the error message
     */
    public ArgumentException(String msg) {
        super(msg);
    }
}
