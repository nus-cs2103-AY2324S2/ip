package duke.exceptions;

/**
 * Exception that will be thrown when user inputs a task to mark that is not in the task list
 */
public class MissingTaskToMarkException extends RuntimeException {

    public MissingTaskToMarkException(String errorMessage) {
        super(errorMessage);
    }
}
