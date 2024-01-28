package exception;

public class DeadlineFormatException extends UncleBobException{

    private static final String EXCEPTION_MESSAGE = "Correct Usage: deadline <message> /by <day/time>";


    public DeadlineFormatException() {
        super(EXCEPTION_MESSAGE);
    }
}
