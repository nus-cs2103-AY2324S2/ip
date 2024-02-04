package osiris.exceptions;

/**
 * Custom exception class for representing errors in parsing stored date and time values in the Osiris application.
 */
public class OsirisParseStoredDateTimeException extends OsirisDateTimeException {

    /**
     * Constructs a new OsirisParseStoredDateTimeException with a default error message.
     * The message indicates that parsing stored date and time values has failed due to potential file corruption.
     */
    public OsirisParseStoredDateTimeException() {
        super("Unable to parse Date Times stored. Storage File may have been corrupted.");
    }
}
