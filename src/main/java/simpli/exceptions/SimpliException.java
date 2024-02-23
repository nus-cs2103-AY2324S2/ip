package simpli.exceptions;

/**
 * General exception for chatbot related errors.
 */
public class SimpliException extends Exception {
    /**
     * Initializes the exception without a message.
     */
    public SimpliException() {
        super();
    }

    /**
     * Initializes the exception with a message.
     *
     * @param errorMsg error message String to be thrown.
     */
    public SimpliException(String errorMsg) {
        super(errorMsg);
    }
}
