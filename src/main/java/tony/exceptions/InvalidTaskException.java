package tony.exceptions;

/**
 * This class represents exceptions thrown when Task input is invalid.
 */
public class InvalidTaskException extends TonyException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
