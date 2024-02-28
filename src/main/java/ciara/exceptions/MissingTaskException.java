package ciara.exceptions;

/**
 * The MissingTaskException class provides an exception for missing
 * tasks in the ciara exception.
 *
 * @author Ryan NgWH
 */
public class MissingTaskException extends CiaraException {
    /**
     * Creates a MissingTaskException
     *
     * @param errorMessage Error message
     */
    public MissingTaskException(String errorMessage) {
        super(errorMessage);
    }
}
