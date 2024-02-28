package ciara.exceptions;

/**
 * The TaskCorruptedException class provides an exception for corrupted tasks in
 * the Ciara application.
 *
 * @author Ryan NgWH
 */
public class TaskCorruptedException extends CiaraException {
    /**
     * Creates a TaskCorruptedException
     *
     * @param errorMessage Error message
     */
    public TaskCorruptedException(String errorMessage) {
        super(errorMessage);
    }
}
