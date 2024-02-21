package snomexceptions;

/**
 * This is the exception thrown when the date entered is not
 * in the format of yyyy-mm-dd.
 */
public class InvalidCommandDateFormatException extends InvalidCommandException {
    public InvalidCommandDateFormatException() {
        super("Please ensure that your date(s) is of the format yyyy-mm-dd");
    }
}
