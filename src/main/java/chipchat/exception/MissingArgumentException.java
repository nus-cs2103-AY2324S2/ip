package chipchat.exception;

/**
 * Represents exceptions arising from missing arguments in the input. Subclass of ArgumentException.
 */
public class MissingArgumentException extends ArgumentException {
    public MissingArgumentException(String msg) {
        super(msg);
    }
}
