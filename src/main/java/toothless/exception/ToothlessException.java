package toothless.exception;

/**
 * Exception class for Toothless chatbot.
 */
public class ToothlessException extends Exception {
    /**
     * A public constructor to initialize the exception.
     *
     * @param message The message for the exception to be thrown.
     */
    public ToothlessException(String message) {
        super(message);
    }
}
