package jerry.command;

/**
 * Represents an exception that is thrown when an error occurs in formatting a command.
 * This exception is used within the chatbot application to signal issues with the user input
 * that cannot be parsed into a valid command due to incorrect formatting.
 * <p>
 * For example, this exception might be thrown if a command that expects additional
 * arguments is provided without those arguments, or if the arguments do not meet
 * the expected format (e.g., a date in the wrong format).
 */
public class CommandFormatException extends Exception {
    /**
     * Constructs a new {@code CommandFormatException} with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param message The detail message which provides more information on the command format error.
     */
    public CommandFormatException(String message) {
        super(message);
    }
}
