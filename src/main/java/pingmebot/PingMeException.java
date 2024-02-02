package pingmebot;

/**
 * Responsible for throwing exception that is specific to the bot application.
 */
public class PingMeException extends Exception {
    protected String message;
    /**
     * Creates a specified bot object that has its own error message
     *
     * @param message Error message.
     */
    public PingMeException(String message) {
        super(message);
        this.message = message;
    }
}
