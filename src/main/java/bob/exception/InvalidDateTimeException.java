package bob.exception;

/**
 * Represents an error indicating that an invalid date and time has been given by the user.
 * An <code>InvalidDateTimeException</code> object corresponds to an error indicating
 * that a date and time of an invalid format has been given by the user.
 */
public class InvalidDateTimeException extends BobException {
    private static final String MESSAGE = "i only understand %s, but you gave me %s";

    /**
     * Returns an error indicating that an invalid date and time has been given by the user.
     *
     * @param format The expected date and time format.
     * @param parsedString The date and time given by the user.
     */
    public InvalidDateTimeException(String format, String parsedString) {
        super(String.format(MESSAGE, format, parsedString));
    }
}
