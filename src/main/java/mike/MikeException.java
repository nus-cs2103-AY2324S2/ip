package mike;

/**
 * The exception class for unsupported behaviour specific to {@link Mike}.
 * @author ningc
 */
public class MikeException extends Exception {

    /**
     * Constructor.
     * @param message The error message with the exception.
     */
    public MikeException(String message) {
        super(message);
    }
}
