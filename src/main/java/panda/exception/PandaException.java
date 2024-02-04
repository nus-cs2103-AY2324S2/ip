package panda.exception;

/**
 * Custom exception class for Panda application.
 * This exception is thrown when an error occurs in the Panda application.
 */
public class PandaException extends Exception {
    /**
     * Constructs a new PandaException with no detail message.
     */
    public PandaException() {
        super();
    }

    /**
     * Constructs a new PandaException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public PandaException(String message) {
        super(message);
    }

    /**
     * Constructs a new PandaException with the specified cause.
     * 
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public PandaException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new PandaException with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public PandaException(String message, Throwable cause) {
        super(message, cause);
    }
}
