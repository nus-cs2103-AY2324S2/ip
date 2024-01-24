package chatbot.exceptions;

public class InvalidArgumentException extends DukeException {
    public static final String ERRSTR = "Invalid input for this command!";
    public InvalidArgumentException() {
        super(ERRSTR);
    }
}
