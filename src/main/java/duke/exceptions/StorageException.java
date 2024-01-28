package duke.exceptions;

/**
 * The StorageException class provides an exception for storage related
 * activities.
 *
 * @author Ryan NgWH
 */
public class StorageException extends DukeException {
    /**
     * Creates a StorageException
     *
     * @param errorMessage Error message
     */
    public StorageException(String errorMessage) {
        super(errorMessage);
    }
}
