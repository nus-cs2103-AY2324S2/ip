package snomexceptions;

/**
 * This is the exception thrown when a user tries
 * to mark a undone task as incomplete.
 */
public class InvalidCommandTaskNotDoneException extends InvalidCommandException {

    public InvalidCommandTaskNotDoneException() {
        super("A task that is not done cannot be marked as undone");
    }
}
