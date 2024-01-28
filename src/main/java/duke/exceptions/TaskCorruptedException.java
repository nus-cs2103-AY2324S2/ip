package duke.exceptions;

/**
 * The TaskCorruptedException class provides an exception for corrupted tasks in
 * the Duke application.
 *
 * @author Ryan NgWH
 */
public class TaskCorruptedException extends DukeException {
    /**
     * Creates a TaskCorruptedException
     *
     * @param errorMessage Error message
     */
    public TaskCorruptedException(String errorMessage) {
        super(errorMessage);
    }
}
