package command;

/**
 * Represents a response to a command.
 * <p>
 * This class is used to represent a response to a command. It provides methods
 * to get the message and error status of the response.
 * </p>
 */
public class CommandResponse {
    private final String message;
    private final boolean isError;

    /**
     * Constructs a new {@code CommandResponse} instance with the specified message
     * and error status.
     * 
     * @param message The message to be associated with the response.
     * @param isError The error status of the response.
     */
    public CommandResponse(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Returns the message associated with the response.
     * 
     * @return The message associated with the response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the error status of the response.
     * 
     * @return The error status of the response.
     */
    public boolean isError() {
        return isError;
    }

    /**
     * Returns a new {@code CommandResponse} instance that represents an erroneous
     * response.
     * 
     * @param message The message to be associated with the response.
     * @return A new {@code CommandResponse} instance with the specified message and
     *         error status.
     */
    public static CommandResponse error(String message) {
        return new CommandResponse(message, true);
    }

    /**
     * Returns a new {@code CommandResponse} instance that represents a successful
     * response.
     * 
     * @param message The message to be associated with the response.
     * @return A new {@code CommandResponse} instance with the specified message and
     *         success status.
     */
    public static CommandResponse success(String message) {
        return new CommandResponse(message, false);
    }
}
