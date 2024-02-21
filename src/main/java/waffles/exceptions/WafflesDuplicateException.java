package waffles.exceptions;

/**
 * The WafflesDuplicateException class represents exceptions specific
 * to invalid arguments in the Waffles chatbot application.
 * It extends the WafflesException class.
 */
public class WafflesDuplicateException extends WafflesException {

    /**
     * Constructs a new WafflesDuplicateException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public WafflesDuplicateException(String errorMessage) {
        super(errorMessage);
    }
}
