package numerator.exceptions.storage;

/**
 * Signals that there is an error loading data from storage.
 */
public class LoadingException extends numerator.exceptions.storage.StorageException {
    /**
     * Constructs a LoadingException with the specified detail message.
     *
     * @param message should contain information about the exception.
     */
    public LoadingException(String message) {
        super(message);
    }
}
