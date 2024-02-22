package cruisey.exception;


/**
 * CruiseyException is a custom exception class for handling exceptions specific to the Duke application.
 * It extends the standard Exception class and provides a customized toString method.
 */
public class CruiseyException extends Exception {

    /**
     * Constructs a CruiseyException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public CruiseyException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the CruiseyException, providing additional context.
     *
     * @return A string representation of the exception.
     */
    public String toString() {
        return "Ummm, " + getMessage();
    }
}
