package luke.exception;

/**
 * Represents an exception when the file cannot be read or written to.
 */
public class FileException extends Exception {
    public FileException(String message) {
        super(message);
    }
}
