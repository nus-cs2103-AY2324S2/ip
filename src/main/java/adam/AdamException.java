package adam;

/**
 * Represents an exception specific to Duke.
 */
public class AdamException extends Exception {
    /**
     * Creates an exception with the provided error message.
     *
     * @param message The error message of the exception.
     */
    public AdamException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        return "ERROR: " + super.getMessage();
    }
}
