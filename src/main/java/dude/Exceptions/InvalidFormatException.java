package dude.Exceptions;

/**
 * The InvalidFormatException class represents an exception that is thrown when the format is invalid.
 */
public class InvalidFormatException extends DudeException {

    /**
     * Constructor for the InvalidFormatException class.
     *
     * @param message The message of the exception.
     */
    public InvalidFormatException(String message) {
        super(message);
    }

    /**
     * Constructor for the InvalidFormatException class.
     *
     * @param command The command that was used.
     * @param format  The format that should be used.
     */
    public InvalidFormatException(String command, String format) {
        super("Invalid format for " + command + " command. \nPlease use this format: " + format + ",\n or type help for more information.");
    }

    /**
     * Constructor for the InvalidFormatException class.
     *
     * @param command The command that was used.
     * @param format The format that should be used.
     * @param note The note to be added to the exception message.
     */
    public InvalidFormatException(String command, String format, String note) {
        super("Invalid format for " + command + " command. " +
                "\nPlease use this format: " + format + "," +
                "\n or type help for more information."
                + "\nNote: " + note);
    }
}