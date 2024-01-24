package exceptions;

public class DukeException extends RuntimeException {

    public DukeException(String error) {
        super(error);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
