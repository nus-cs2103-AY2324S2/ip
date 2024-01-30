package exceptions;

/**
 * The {@code ConvoBotException} class represents an exception specific to the ConvoBot application.
 * It extends the standard {@code Exception} class and provides additional constructors for
 * customized exception handling.
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
     * @param message the detail message (which is saved for later retrieval by the {@code getMessage()} method)
     */
    public ConvoBotException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ConvoBotException} with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@code getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@code getCause()} method)
     */
    public ConvoBotException(String message, Throwable cause) {
        super(message, cause);
    }
}
