package Duke.Exception;

public class InvalidDateTimeException extends DukeException {

    public InvalidDateTimeException(String message) {
        super(message);
    }

    @Override
    public String errorMessage() {
        return null;
    }
}
