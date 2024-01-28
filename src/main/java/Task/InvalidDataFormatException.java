package task;

public class InvalidDataFormatException extends DukeException {
    public InvalidDataFormatException(String message, String botMessage) {
        super(message, botMessage);
    }
}
