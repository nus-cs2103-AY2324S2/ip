package nicky;

/**
 * Represents custom exceptions specific to the Nicky application.
 * It extends the Exception class and is used to handle application-specific error messages.
 */
public class NickyException extends Exception {
    /**
     * Creates a new NickyException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public NickyException(String message) {
        super(message);
    }
}

