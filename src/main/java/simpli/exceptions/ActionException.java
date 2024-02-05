package simpli.exceptions;

/**
 * Thrown when no such chatbot action exists.
 */
public class ActionException extends SimpliException {
    /**
     * Initializes the exception without a message.
     */
    public ActionException() {
        super();
    }

    /**
     * Initializes the exception with a message.
     *
     * @param errorMsg Error message String to be thrown.
     */
    public ActionException(String errorMsg) {
        super(errorMsg);
    }
}
