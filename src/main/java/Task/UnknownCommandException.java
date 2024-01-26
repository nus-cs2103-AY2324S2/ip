package task;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message, String botMessage) {
        super(message, botMessage);
    }
}
