package asher;

/**
 * Represents the exception class specific to the bot.
 */
public class BotException extends Exception {

    /**
     * Constructs a new BotException with the error message.
     *
     * @param message The message of error.
     */
    public BotException(String message) {
        super(message);
    }
}
