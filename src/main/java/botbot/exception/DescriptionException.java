package botbot.exception;

public class DescriptionException extends BotBotException {
    public DescriptionException() {
        super("Please include a description for the task!");
    }
}
