package duke.exceptions;

/**
 * Exception that will be thrown when user inputs a task to unmark that is not in the task list
 */
public class MissingTaskToUnmarkException extends RuntimeException {

    public MissingTaskToUnmarkException(String errorMessage) {
        super(errorMessage);
    }
}

