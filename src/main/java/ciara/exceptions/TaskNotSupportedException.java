package ciara.exceptions;

/**
 * The TaskNotSupportedException class provides an exception for unspported
 * tasks in the Ciara application.
 *
 * @author Ryan NgWH
 */
public class TaskNotSupportedException extends CiaraException {
    /**
     * Creates a TaskNotSupportedException
     *
     * @param errorMessage Error message
     */
    public TaskNotSupportedException(String errorMessage) {
        super(errorMessage);
    }
}
