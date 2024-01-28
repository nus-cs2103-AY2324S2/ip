package duke.exceptions;

/**
 * The TaskNotSupportedException class provides an exception for unspported
 * tasks in the Duke application.
 *
 * @author Ryan NgWH
 */
public class TaskNotSupportedException extends DukeException {
    /**
     * Creates a TaskNotSupportedException
     *
     * @param errorMessage Error message
     */
    public TaskNotSupportedException(String errorMessage) {
        super(errorMessage);
    }
}
