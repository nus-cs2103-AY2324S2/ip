package pingmebot;

<<<<<<< HEAD:src/main/java/pingmebot/myBotException.java
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
=======
public class PingMeException extends Exception {
    protected String message;
    public PingMeException(String message) {
>>>>>>> branch-A-CodingStandard:src/main/java/pingmebot/PingMeException.java
        super(message);
        this.message = message;
    }
}
