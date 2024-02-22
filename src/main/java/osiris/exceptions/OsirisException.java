package osiris.exceptions;

/**
 * Custom exception class for Osiris application.
 * Extends RuntimeException to indicate unchecked exceptions.
 */
public class OsirisException extends RuntimeException {

    /**
     * Constructs a new OsirisException with the specified detail message.
     *
     * @param message the detail message.
     */
    public OsirisException(String message) {
        super(message);
    }
}
