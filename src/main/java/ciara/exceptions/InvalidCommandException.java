package ciara.exceptions;

/**
 * The InvalidCommandException class provides an exception for invalid
 * commands in the ciara application
 *
 * @author Ryan NgWH
 */
public class InvalidCommandException extends CiaraException {
    /**
     * Creates a InvalidCommandException
     *
     * @param errorMessage Error message
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
