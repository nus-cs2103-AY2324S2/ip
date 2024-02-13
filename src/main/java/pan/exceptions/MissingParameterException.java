package pan.exceptions;

/**
 * MissingParameterException - A custom exception
 * @author Jerome Goh
 */
public class MissingParameterException extends Exception {
    /**
     * Creates a MissingParameterException instance.
     *
     * @param message error message of the given exception
     */
    public MissingParameterException(String message) {
        super("Missing Parameter Exception: " + message);
    }
}
