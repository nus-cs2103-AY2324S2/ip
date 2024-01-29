package exception;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("Index is out of bounds");
    }
}
