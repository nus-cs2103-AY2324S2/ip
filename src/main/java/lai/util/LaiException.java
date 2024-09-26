package lai.util;

/**
 * Represents a custom exception type for the Lai application.
 * This exception is used to handle application-specific errors and provide meaningful error messages to the user.
 */
public class LaiException extends Exception {
    /**
     * Constructs a new LaiException with the specified message.
     *
     * @param message The message for the exception thrown.
     */
    public LaiException (String message) {
        super(message);
    }

    /**
     * Returns a string representation of the LaiException, including the message that was provided at the time of
     * creation.
     * The format of the return string is "Error occurred: " followed by the message.
     *
     * @return A string representation of the LaiException, containing the message.
     */
    @Override
    public String toString() {
        return String.format("Error occurred: " + getMessage());
    }
}
