package duke.exception;

/**
 * Exception thrown when user attempts to operate on an invalid entry in the task list
 */
public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
        super("Please enter a valid task number!");
    }
}
