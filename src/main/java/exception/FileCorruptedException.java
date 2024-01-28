package exception;

/**
 * Exception thrown when a file is found to be corrupted.
 */
public class FileCorruptedException extends NarutoException {
    /**
     * Constructs a new FileCorruptedException with the specified detail message.
     *
     * @param message the detail message
     */
    public FileCorruptedException(String message) {
        super(message);
    }

    /**
     * Returns the error type associated with this exception.
     *
     * @return the error type
     */
    @Override
    public ErrorType getErrorType() {
        return ErrorType.FILE_CORRUPTED;
    }
}
