package osiris.exceptions;

/**
 * Custom exception class for representing date and time parsing errors in the Osiris application.
 */
public class OsirisParseDateTimeException extends OsirisDateTimeException {

    /**
     * Constructs a new OsirisParseDateTimeException with the specified detail message.
     * The message indicates that the date-time string parsing has failed and suggests the correct format.
     *
     * @param dateTimeStr the date-time string that failed to parse.
     */
    public OsirisParseDateTimeException(String dateTimeStr) {
        super("Failed to parse the date-time string: "
                + dateTimeStr + "\nPlease provide date time range 'dd-MM-yyyy HHmm' format.");
    }
}
