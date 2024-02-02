package harper.exceptions;

/**
 * Exception that indicates error occurs during creating folder or file.
 */
public class HarperFileCreatingException extends HarperException {
    public HarperFileCreatingException() {
        super("Error occurs during creating folder and file!");
    }
}
