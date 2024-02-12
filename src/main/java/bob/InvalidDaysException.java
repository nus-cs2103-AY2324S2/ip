package bob;

public class InvalidDaysException extends BobException {
    private static final String MESSAGE = "how many days is %s days???";

    public InvalidDaysException(String parsedString) {
        super(String.format(MESSAGE, parsedString));
    }
}
