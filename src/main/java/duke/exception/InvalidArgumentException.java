package duke.exception;

public class InvalidArgumentException extends DukeException{

    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public String errorMessage() {
        return "Invalid Arguments - " + getMessage();
    }
}
