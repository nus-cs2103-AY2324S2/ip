package nollid.exceptions;

/**
 * InvalidCommandException class represents an exception specific to invalid commands.
 * It extends the NollidException class.
 */
public class InvalidCommandException extends NollidException {
    /**
     * Constructor for InvalidCommandException.
     *
     * @param errorMessage String containing the error message.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}

