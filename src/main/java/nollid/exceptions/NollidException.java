package nollid.exceptions;

/**
 * NollidException class represents a custom exception for the Nollid application.
 * It extends the Exception class and includes a constructor to set the error message.
 */
public class NollidException extends Exception {
    /**
     * Constructor for NollidException.
     *
     * @param errorMessage String containing the error message.
     */
    public NollidException(String errorMessage) {
        super(errorMessage);
    }
}
