package duke.exception;

public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
        super("Please enter a valid task number!");
    }
}
