package waffles.exceptions;

/**
 * The WafflesUnknownCommandException class represents exceptions specific
 * to unknown commands in the Waffles chatbot application.
 * It extends the WafflesException class.
 */
public class WafflesUnknownCommandException extends WafflesException {

    /**
     * Constructs a new WafflesUnknownCommandException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public WafflesUnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
