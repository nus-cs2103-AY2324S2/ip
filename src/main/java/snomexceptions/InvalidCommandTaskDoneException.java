package snomexceptions;

/**
 * This is the exception thrown when a user tries
 * to mark a already done task as complete.
 */
public class InvalidCommandTaskDoneException extends InvalidCommandException {
    public InvalidCommandTaskDoneException() {
        super("A task that is already done cannot be marked as done");
    }
}
