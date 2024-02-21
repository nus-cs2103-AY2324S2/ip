package luke.exception;

/**
 * Thrown when the date given by the user is not in "yyyy-MM-dd HH:mm" format.
 */
public class InvalidDateTimeException extends Throwable {
    @Override
    public String toString() {
        return "\nThe date provided is invalid.\n"
                + "Please type the date in this format: yyyy-MM-dd HH:mm";
    }
}
