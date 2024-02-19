package cvb.exceptions;

/**
 * Represents an exception specific to the ConvoBot application.
 * Extends the standard {@code Exception} class and provides
 * additional constructors for customized exception handling.
 */
public class ConvoBotException extends Exception {

    /**
     * Constructs a new {@code ConvoBotException} with no specified detail message.
     */
    public ConvoBotException() {
        super();
    }

    /**
     * Constructs a new {@code ConvoBotException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@code getMessage()} method).
     */
    public ConvoBotException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ConvoBotException} with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the {@code getMessage()} method).
     * @param cause   The cause (which is saved for later retrieval by the {@code getCause()} method).
     */
    public ConvoBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
