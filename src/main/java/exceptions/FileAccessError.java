package exceptions;

/**
 *  Represents exception relating to error accessing files
 */
public class FileAccessError extends BlawgException {
    public FileAccessError() {
        super();
    }
    public FileAccessError(String message) {
        super(message);
    }
}
