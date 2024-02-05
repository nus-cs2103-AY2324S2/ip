package eggy.exception;

/**
 * Represents an exception when the user inputs an invalid datetime format.
 */
public class DateTimeFormatException extends EggyException {
    /**
     * Constructs a DateTimeFormatException.
     */
    public DateTimeFormatException() {
        super(" Invalid datetime format. Please use the format d/MM/yyyy HHmm.");
    }
}
