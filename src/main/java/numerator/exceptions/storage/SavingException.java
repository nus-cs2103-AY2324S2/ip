package numerator.exceptions.storage;

/**
 * Signals that there is an error saving data to storage.
 */
public class SavingException extends numerator.exceptions.storage.StorageException {
    /**
     * Constructs a SavingException with the specified detail message.
     *
     * @param message should contain information about the exception.
     */
    public SavingException(String message) {
        super(message);
    }
}
