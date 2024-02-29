package dude.exceptions;

/**
 * The TaskListFullException class represents an exception that is thrown when the task list is full.
 */
public class TaskListFullException extends DudeException {

    /**
     * Constructor for the TaskListFullException class.
     *
     * @param message The message of the exception.
     */
    public TaskListFullException(String message) {
        super(message);
    }
}
