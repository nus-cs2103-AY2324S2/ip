package numerator.exceptions.storage;

import numerator.exceptions.NumeratorException;

/**
 * Signals that an exception has occurred while accessing storage.
 */
public class StorageException extends NumeratorException {
    /**
     * Constructs a StorageException with the specified detail message.
     *
     * @param message should contain information about the exception.
     */
    public StorageException(String message) {
        super(message);
    }
}
