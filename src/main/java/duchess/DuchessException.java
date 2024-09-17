package duchess;

/**
 * DuchessException is a custom exception class specific to the Duchess program.
 * It extends the Exception class to represent exceptions that occur within Duchess.
 */
public class DuchessException extends Exception {
    /**
     * Constructs a DuchessException with the specified detail message.
     *
     * @param message the detail message
     */
    public DuchessException(String message) {
        super(message);
    }
}
