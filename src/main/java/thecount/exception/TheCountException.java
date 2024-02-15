package thecount.exception;

/**
 * The base exception class for exceptions specific to the Count application.
 */
public class TheCountException extends Exception {

    /**
     * Constructs a new TheCountException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public TheCountException(String message) {
        super(message);
    }
}
