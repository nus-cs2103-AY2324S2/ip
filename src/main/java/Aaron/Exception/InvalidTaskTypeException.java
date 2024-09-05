package aaron.exception;

/**
 * class that represents an exception when the user inputs an unrecognized task
 * type to
 * be added to the aaronbot tasklist
 */
public class InvalidTaskTypeException extends TaskErrorException {
    public InvalidTaskTypeException(String e) {
        super(e);
    }
}
