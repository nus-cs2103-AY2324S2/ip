package duke.exceptions;

/**
 * Exception thrown when user does not input a task name for creating a todo task.
 */
public class MissingToDoNameException extends RuntimeException {

    public MissingToDoNameException(String errorMessage) {
        super(errorMessage);
    }
}
