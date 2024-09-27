package alastor;

/**
 * Represents an exception specific to Alastor.
 */
public class AlastorException extends Exception {

    /**
     * Constructs an AlastorException with the specified detail message.
     *
     * @param message The detail message.
     */
    public AlastorException(String message) {
        super(message);
        assert message != null : "Message should not be null";
    }
}
