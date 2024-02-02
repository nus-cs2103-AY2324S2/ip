package bytebuddy.exceptions;


/**
 * Custom exception class for handling Duke-related exceptions.
 */
public class ByteBuddyException extends Exception {

    /**
     * Constructs a ByteBuddyException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public ByteBuddyException(String message) {
        super(message);
    }

    /**
     * Overrides the getMessage() method to prepend a specific message to the original error message.
     *
     * @return The formatted error message with the added prefix for personality.
     */
    @Override
    public String getMessage() {
        return String.format("\t holup!! %s", super.getMessage());
    }
}
