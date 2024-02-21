package capone.exceptions;

/**
 * Custom exception class for handling corrupted task lists.
 * Extends the CaponeException class.
 *
 * @author Tay Rui-Jie
 */
public class TaskListCorruptedException extends CaponeException {
    /**
     * Constructs a new TaskListCorruptedException with the specified error message.
     *
     * @param errorMessage The error message associated with the task list corruption exception.
     */
    public TaskListCorruptedException(String errorMessage) {
        super(errorMessage);
    }
}
