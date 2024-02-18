package exception;

/**
 * Represents exceptions specific to the GeePeeTee application.
 * <p>
 * This class extends the {@link Exception} class and provides a
 * mechanism to handle errors that are specific to the GeePeeTee
 * application's operations.
 * <p>
 * The class allows for the creation of {@code GeePeeTeeException} instances
 * with custom messages.
 * </p>
 */
public class GeePeeTeeException extends Exception {
    /**
     * Constructs a new {@code GeePeeTeeException} instance with the specified
     * error message.
     * 
     * @param message The error message to be associated with the exception.
     */
    public GeePeeTeeException(String message) {
        super(message);
    }

    /**
     * Returns the error message associated with the exception.
     * 
     * @return The error message associated with the exception.
     */
    public String getErrorMessage() {
        return super.getMessage();
    }
}
