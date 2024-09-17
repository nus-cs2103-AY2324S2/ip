package snomexceptions;

/**
 * This is the exception thrown when the user leaves the task description or date blank.
 */
public class InvalidCommandTaskDescException extends InvalidCommandException {
    public InvalidCommandTaskDescException() {
        super("Please do not leave your task description or date blank");
    }
}
