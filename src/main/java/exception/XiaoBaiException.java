package exception;

/**
 * Represents an exception specific to the XiaoBai application.
 * This exception can be thrown when there is an error or unexpected condition
 * in the XiaoBai application.
 */
public class XiaoBaiException extends Exception {

    /**
     * Constructs a new XiaoBaiException with no detail message.
     */
    public XiaoBaiException() {
        super();
    }

    /**
     * Constructs a new XiaoBaiException with the specified detail message.
     * 
     * @param message The detail message.
     */
    public XiaoBaiException(String message) {
        super(message);
    }

    /**
     * Overrides the getMessage() method to return only the error message
     * without the exception class name.
     * 
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
