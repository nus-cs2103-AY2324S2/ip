package ghbot;

/**
 * GHBotException Class.
 */
public class GHBotException extends Exception {
    /**
     * DukeException Constructor.
     * @param errorMessage A error message.
     */
    public GHBotException(String errorMessage){
        super(errorMessage);
    }
}
