package bob.exception;

/**
 * Represents an error indicating that an invalid number of days has been given by the user.
 * An <code>InvalidDaysException</code> object corresponds to an error indicating
 * that the number of days given by the user is not an integer.
 */
public class InvalidDaysException extends BobException {
    private static final String MESSAGE = "how many days is %s days???";

    /**
     * Returns an error indicating that an invalid number of days has been given by the user.
     *
     * @param parsedString The number of days given by the user.
     */
    public InvalidDaysException(String parsedString) {
        super(String.format(MESSAGE, parsedString));
    }
}
