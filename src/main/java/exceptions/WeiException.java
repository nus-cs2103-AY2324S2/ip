package exceptions;

/**
 * Indicates error caused by the execution of the chatbot.
 */
public class WeiException extends Exception {
    /**
     * Creates a WeiException.
     *
     * @param error the message of the exception raised.
     */
    public WeiException(String error) {
        super(error);
    }
}
