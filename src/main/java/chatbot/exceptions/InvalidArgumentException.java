package chatbot.exceptions;

public class InvalidArgumentException extends PlanaException {
    public static final String ERRSTR = "Invalid input for this command!";
    public InvalidArgumentException() {
        super(ERRSTR);
    }
}
