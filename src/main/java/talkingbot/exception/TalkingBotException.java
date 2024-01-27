package talkingbot.exception;

/**
 * Represents any exception that may occur during the running
 * of the program.
 */
public class TalkingBotException extends Exception {

    /**
     * Constructor for the TalkingBotException class.
     *
     * @param errorMessage String indicating the error message.
     */
    public TalkingBotException(String errorMessage) {
        super(errorMessage);
    }
}
