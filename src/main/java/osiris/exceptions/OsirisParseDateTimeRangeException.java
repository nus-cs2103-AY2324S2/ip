package osiris.exceptions;

/**
 * Custom exception class for representing errors in parsing date and time ranges in the Osiris application.
 */
public class OsirisParseDateTimeRangeException extends OsirisDateTimeException {

    /**
     * Constructs a new OsirisParseDateTimeRangeException with a default error message.
     * The message indicates that parsing the date time range has failed and suggests the correct format.
     */
    public OsirisParseDateTimeRangeException() {
        super("Failed to parse the date time range."
                + "\nPlease provide date time range 'dd-MM-yyyy HHmm' format.");
    }
}
