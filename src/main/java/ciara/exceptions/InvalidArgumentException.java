package ciara.exceptions;

/**
 * The MissingArgumentException class provides an exception for invalid
 * arguments in the ciara application
 *
 * @author Ryan NgWH
 */
public class InvalidArgumentException extends CiaraException {
    /**
     * Creates an InvalidArgumentException
     *
     * @param errorMessage Error message
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
