package osiris.exceptions;

/**
 * Custom exception class for representing date and time related errors in the Osiris application.
 */
public class OsirisDateTimeException extends OsirisException {

    /**
     * Constructs a new OsirisDateTimeException with the specified detail message.
     *
     * @param message the detail message.
     */
    public OsirisDateTimeException(String message) {
        super(message);
    }
}
