package ghbot.exception;

/**
 * GhBotException Class.
 */
public class GhBotException extends Exception {
    /**
     * DukeException Constructor.
     * @param errorMessage An error message.
     */
    public GhBotException(String errorMessage) {
        super(errorMessage);
    }
}
