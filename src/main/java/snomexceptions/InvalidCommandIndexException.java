package snomexceptions;

/**
 * This is the exception thrown when the user enters an invalid index
 * when selecting a task.
 */
public class InvalidCommandIndexException extends InvalidCommandException {
    public InvalidCommandIndexException() {
        super("Please ensure that the index you entered is valid");
    }
}
