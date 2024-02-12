package bob.exception;

public class InvalidDateTimeException extends BobException {
    private static final String MESSAGE = "i only understand %s, but you gave me %s";

    public InvalidDateTimeException(String format, String parsedString) {
        super(String.format(MESSAGE, format, parsedString));
    }
}
