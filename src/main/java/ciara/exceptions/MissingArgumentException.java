package ciara.exceptions;

/**
 * The MissingArgumentException class provides an exception for missing
 * arguments in the ciara exception.
 *
 * @author Ryan NgWH
 */
public class MissingArgumentException extends CiaraException {
    /**
     * Creates a MissingArgumentException
     *
     * @param errorMessage Error message
     */
    public MissingArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
