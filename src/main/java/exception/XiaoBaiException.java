package exception;

/**
 * Represents an exception specific to the Duke application.
 * This exception can be thrown when there is an error or unexpected condition
 * in the Duke application.
 */
public class XiaoBaiException extends Exception {
    public XiaoBaiException() {
        super();
    }

    public XiaoBaiException(String message) {
        super(message);
    }
}
