package pingmebot;

/**
 * Responsible for throwing exception that is specific to the bot.
 */
public class myBotException extends Exception {
    protected String message;

    /**
     * Creates a specified bot object that has its own error message
     *
     * @param message Error message.
     */
    public myBotException(String message) {
        super(message);
        this.message = message;
    }
}
