package waffles.exceptions;

/**
 * The WafflesIllegalArgumentException class represents exceptions specific
 * to invalid arguments in the Waffles chatbot application.
 * It extends the WafflesException class.
 */
public class WafflesIllegalArgumentException extends WafflesException {

    /**
     * Constructs a new WafflesIllegalArgumentException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public WafflesIllegalArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
