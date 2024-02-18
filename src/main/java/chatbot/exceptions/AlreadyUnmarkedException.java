package chatbot.exceptions;

public class AlreadyUnmarkedException extends PlanaException {
    public static final String ERRSTR = "This task is already unmarked!";
    public AlreadyUnmarkedException() {
        super(ERRSTR);
    }
}
