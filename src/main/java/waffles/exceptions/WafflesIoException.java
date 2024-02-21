package waffles.exceptions;

/**
 * The WafflesIoException class represents exceptions specific to input/output errors
 * in the Waffles chatbot application.
 * It extends the WafflesException class.
 */
public class WafflesIoException extends WafflesException {

    /**
     * Constructs a new afflesIOException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public WafflesIoException(String errorMessage) {
        super(errorMessage);
    }
}
