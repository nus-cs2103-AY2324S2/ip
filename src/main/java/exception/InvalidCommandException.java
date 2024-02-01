package exception;

public class InvalidCommandException extends DukeException {
    private String message;

    public InvalidCommandException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "\n    OOPS!!! Something went wrong D:\n";
    }
}
