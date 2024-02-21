package harvard.exceptions;

/**
 * Represents an exception specific to the Harvard application.
 * Extends the Exception class.
 */
public class HarvardException extends Exception {

    /**
     * Constructs a HarvardException object with the specified error message.
     *
     * @param errorMessage the error message associated with the exception
     */
    public HarvardException(String errorMessage) {
        super(errorMessage);
    }
}
