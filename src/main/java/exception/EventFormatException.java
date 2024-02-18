package exception;

/**
 * Exception indicating an incorrect format for the event input.
 */
public class EventFormatException extends UncleBobException {
    private static final String EXCEPTION_MESSAGE = "Correct Usage: event <message> /from <day/time> /to <day/time>";

    public EventFormatException() {
        super(EXCEPTION_MESSAGE);
    }
}
