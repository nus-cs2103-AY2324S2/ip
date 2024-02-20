package dibo.exception;

/**
 * The DiboException class represents an exception that occurs when interacting with Dibo.
 */
public class DiboException extends Exception {

    /**
     * Constructs a new DiboException object with the specified parameters.
     *
     * @param errorMessage The error message of the exception.
     */
    public DiboException(String errorMessage) {
        super(errorMessage);
    }

}
