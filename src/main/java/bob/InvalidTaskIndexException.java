package bob;

public class InvalidTaskIndexException extends BobException {
    private static final String MESSAGE_NAN = "%s? that's not even a number";
    private static final String MESSAGE_OUT_OF_BOUNDS = "bro i want a number between 1 and %s but you gave me %s";

    public InvalidTaskIndexException(String parsedString) {
        super(String.format(MESSAGE_NAN, parsedString));
    }

    public InvalidTaskIndexException(String parsedString, int numberOfTasks) {
        super(String.format(MESSAGE_OUT_OF_BOUNDS, numberOfTasks, parsedString));
    }
}
