package exceptions;

/**
 * The EmisException class represents an exception specific to the EMIS application.
 * It is a subclass of the Java Exception class and is used to handle errors and exceptional conditions within the EMIS application.
 */
public class EmisException extends Exception {
    /**
     * Constructs a new EmisException object with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public EmisException(String errorMessage) {
        super(errorMessage);
    }
}
