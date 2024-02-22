package nollid.exceptions;

/**
 * InvalidArgumentException class represents an exception for invalid arguments in the Nollid application.
 * It extends the NollidException class.
 */
public class InvalidArgumentException extends NollidException {

    /**
     * Constructs an InvalidArgumentException with the specified error message.
     *
     * @param errorMessage The error message for the InvalidArgumentException.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
