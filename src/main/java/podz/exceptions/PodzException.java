package podz.exceptions;

/**
 * Represents a custom exception for Podz application.
 */
public class PodzException extends Exception {

    /**
     * Constructs a PodzException object with the specified error message.
     * 
     * @param msg the error message for the exception.
     */
    public PodzException(String msg) {
        super(msg);
    }

    /**
     * Returns a string representation of the exception.
     * 
     * @return a string representing the exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
