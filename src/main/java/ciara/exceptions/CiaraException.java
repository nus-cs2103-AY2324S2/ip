package ciara.exceptions;

/**
 * The CiaraException class defines an exception specific to the Ciara
 * application.
 *
 * @author Ryan NgWH
 */
public class CiaraException extends Exception {
    /**
     * Creates a CiaraException
     *
     * @param errorMessage Error message
     */
    public CiaraException(String errorMessage) {
        super(errorMessage);
    }
}
