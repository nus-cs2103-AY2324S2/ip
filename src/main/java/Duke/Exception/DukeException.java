package Duke.Exception;

public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public abstract String errorMessage();
}
