package dude.Exceptions;

public class InvalidFormatException extends DudeException {

    public InvalidFormatException(String message) {
        super(message);
    }

    //Used to indicate that the format is invalid
    public InvalidFormatException(String command, String format) {
        super("Invalid format for " + command + " command. \nPlease use this format: " + format + ",\n or type help for more information.");
    }

    public InvalidFormatException(String command, String format, String note) {
        super("Invalid format for " + command + " command. " +
                "\nPlease use this format: " + format + "," +
                "\n or type help for more information."
                + "\nNote: " + note);
    }
}