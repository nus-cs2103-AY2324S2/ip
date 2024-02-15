package seedu.duke.exception;

/**
 * Represents an exception to be thrown when saving or loading the tasks
 */
public class StorageOperationException extends DukeException {
    /**
     * Constructor of StorageOperationException
     *
     * @param errorMessage The error message to be shown
     */

    public StorageOperationException(String errorMessage) {
        super(errorMessage);
    }
}
