package waffles.exceptions;

/**
 * The WafflesException class represents exceptions specific to the Waffles chatbot application.
 * It extends the RuntimeException class.
 */
public class WafflesException extends RuntimeException {

    /**
     * Constructs a new WafflesException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public WafflesException(String errorMessage) {
        super(errorMessage);
    }
}
