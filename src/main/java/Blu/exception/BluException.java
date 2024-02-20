package blu.exception;

/**
 * Represents exceptions specific to the Blu application.
 */
public class BluException extends Exception {

    /**
     * Constructs a new BluException with the specified detail message.
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     *
     * @param message The detail message which provides more information about the exception.
     */
    public BluException(String message) {
        super(message);
    }
}
