package exception;

/**
 * Exception indicating an incorrect format for the find input.
 */
public class FindFormatException extends UncleBobException {
    private static final String EXCEPTION_MESSAGE = "Correct Usage: find <message>";


    public FindFormatException() {
        super(EXCEPTION_MESSAGE);
    }
}
