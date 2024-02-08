package ghbot.exception;

/**
 * GhBotException Class.
 */
public class GhBotException extends Exception {
    /**
     * DukeException Constructor.
     * @param errorMessage A error message.
     */
    public GhBotException(String errorMessage) {
        super(errorMessage);
    }
}
