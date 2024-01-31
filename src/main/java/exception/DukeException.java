package exception;

public class DukeException extends Exception {
    String message;
    public DukeException() {
    }

    @Override
    public String getMessage() {
        return "OOPS!!! Something went wrong.";
    }
}
