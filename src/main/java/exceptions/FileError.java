package exceptions;

/**
 *  Represents exception relating to reading or writing to files
 */
public class FileError extends BlawgException {
    public FileError() {
        super();
    }
    public FileError(String message) {
        super(message);
    }
}
