package exceptions;

/**
 * The DeadlineException class represents an exception specific to the EMIS application.
 * It is a subclass of the EmisException class and is used to handle errors and exceptional conditions within the EMIS application.
 */
public class DeadlineException extends EmisException {
    /**
     * Constructs a new DeadlineException object with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DeadlineException(String errorMessage) {
        super(errorMessage);
    }
}