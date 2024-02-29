package duke;

/**
 * This class represents a custom exception for the bot
 * It extends the Exception class, allowing us to throw it when the bot
 * encounters an error
 */
public class BotException extends Exception {
    /**
     * Constructs a new BotException with a specified error message
     * The error message is wrapped with a separator line for better visibility in
     * the terminal
     *
     * @param errMessage The error message for this exception
     */
    public BotException(String errMessage) {
        super(Ui.wrapWithSepLine("Error: " + errMessage));
    }
}