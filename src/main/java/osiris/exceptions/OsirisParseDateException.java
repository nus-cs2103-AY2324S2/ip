package osiris.exceptions;

/**
 * Custom exception class for representing date parsing errors in the Osiris application.
 */
public class OsirisParseDateException extends OsirisDateTimeException {

    /**
     * Constructs a new OsirisParseDateExcpetion with the specified detail message.
     * The message indicates that the date-time string parsing has failed and suggests the correct format.
     *
     * @param dateStr the date-time string that failed to parse.
     */
    public OsirisParseDateException(String dateStr) {
        super("Failed to parse the date-time string: "
                + dateStr + "\nPlease try /by dd-mm-yyyy for a deadline tasks.");
    }
}
